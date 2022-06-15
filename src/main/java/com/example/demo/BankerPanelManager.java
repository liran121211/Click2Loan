package com.example.demo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.MotionBlur;
import javafx.util.Duration;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankerPanelManager {
    private final Scene scene;
    private final static int WINDOW_WIDTH = 650;
    private final static int WINDOW_HEIGHT = 650;
    private final PropertyChangeSupport notifier;


    public BankerPanelManager(Scene scene) {
        this.scene = scene;
        this.notifier = new PropertyChangeSupport(this);
    }


    public void initializeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bankerPanel.fxml"));
            scene.setRoot(loader.load());
            scene.getWindow().setWidth(WINDOW_WIDTH);
            scene.getWindow().setHeight(WINDOW_HEIGHT);
            scene.setUserData(loader);

            BankerPanelController controller = loader.getController();
            notifier.addPropertyChangeListener(controller); // to notify controller
            controller.initManager(this);
            countStatus(controller);

            // Infinity Thread worker to check for new messages
            //--------------------------------
            fetchMessages();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                try {
                    fetchMessages();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            //--------------------------------

        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goLogin() {
        LoginManager loginManager = new LoginManager(scene);
        loginManager.initializeScreen();
    }

    public void edit() {
        EditProfileManager editProfileManager = new EditProfileManager(scene);
        editProfileManager.initializeScreen();
    }

    public void manageMessages() {
        MessagesPanelManager manageMessagesManager = new MessagesPanelManager(scene);
        manageMessagesManager.initializeScreen();
    }

    public void showTodoList() {
        TodoListManager todoListManager = new TodoListManager(scene);
        todoListManager.initializeScreen();
    }

    /**
     * Retrieve all the messages belong to the specific user.
     */
    private void fetchMessages() throws SQLException {
        String[][] unread_messages = LoanApp.sql.select("mailbox", "read", String.format("read=CAST(0 AS BIT),receiver=%s", LoginManager.logged_in_user.getInt("userid", -1)));
        if (unread_messages.length > 0)
            notifier.firePropertyChange("NEW_MESSAGE", 0, unread_messages.length);
        if (unread_messages.length == 0)
            notifier.firePropertyChange("NO_NEW_MESSAGES", -1, unread_messages.length);
    }

    public void commitChange(TableView<LoansModel> items_list, TableColumn.CellEditEvent<LoansModel, String> string_modified_data, TableColumn.CellEditEvent<LoansModel, Double> double_modified_data) throws SQLException {
        if (items_list.getSelectionModel().getSelectedItem() != null) { // if row selected
            //System.out.println(items_list.getFocusModel().getFocusedCell().getColumn());

            if (double_modified_data != null && double_modified_data.getNewValue().compareTo(double_modified_data.getOldValue()) != 0) { // if value did change
                int loan_id = items_list.getSelectionModel().getSelectedItem().getId();
                double remaining_loan = Double.parseDouble(LoanApp.sql.select("loans", "remaining_amount", String.format("id=%s", loan_id))[0][0]);

                if (items_list.getFocusModel().getFocusedCell().getColumn() == 3) {  //if loan_amount column selected
                    LoanApp.sql.update("loans", "loan_amount", String.format("%s", double_modified_data.getNewValue()), String.format("id=%s", loan_id));
                    if (double_modified_data.getNewValue() > double_modified_data.getOldValue()) // update remaining amount accordingly
                        LoanApp.sql.update("loans", "remaining_amount", String.format("%s", remaining_loan + Math.abs(double_modified_data.getOldValue() - double_modified_data.getNewValue())), String.format("id=%s", loan_id));
                    if (double_modified_data.getNewValue() < double_modified_data.getOldValue()) // update remaining amount accordingly
                        LoanApp.sql.update("loans", "remaining_amount", String.format("%s", remaining_loan - Math.abs(double_modified_data.getOldValue() - double_modified_data.getNewValue())), String.format("id=%s", loan_id));
                    if (double_modified_data.getNewValue() < remaining_loan) // update remaining amount accordingly
                        LoanApp.sql.update("loans", "remaining_amount", String.format("%s", 0.0), String.format("id=%s", loan_id));
                }
            }

            if (string_modified_data != null && string_modified_data.getNewValue().compareTo(string_modified_data.getOldValue()) != 0) { // if value did change
                int loan_id = items_list.getSelectionModel().getSelectedItem().getId();

                if (items_list.getFocusModel().getFocusedCell().getColumn() == 5)  //if status column selected
                    LoanApp.sql.update("loans", "status", String.format("%s", string_modified_data.getNewValue()), String.format("id=%s", loan_id));
            }
            countStatus(((FXMLLoader) scene.getUserData()).getController());
        }
    }

    private void countStatus(BankerPanelController c) {
        try {
            String[][] status_counter = LoanApp.sql.select("loans", "status");
            int approved = 0, pending = 0, rejected = 0;
            for (String[] status : status_counter) {
                if (status[0].compareTo("APPROVED") == 0)
                    approved++;
                if (status[0].compareTo("PENDING") == 0)
                    pending++;
                if (status[0].compareTo("REJECTED") == 0)
                    rejected++;
            }

            c.getRejected_count().setText("(" + rejected + " Rejected)");
            c.getPending_count().setText("(" + pending + " Pending)");
            c.getApproved_count().setText("(" + approved + " Approved)");

            if (pending > 0 && !LoanApp.isBankerPendingMessageSeen) {
                c.getUnderstand_img().setVisible(true);
                c.getNote_img().setVisible(true);
                c.getMain_pane().setEffect(new MotionBlur(0.0, 63.0));
                c.getPopup_pane().setVisible(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void inspectLoan() {
        InspectLoanManager inspectLoanManager = new InspectLoanManager(scene);
        inspectLoanManager.initializeScreen(((BankerPanelController) ((FXMLLoader) scene.getUserData()).getController()).getLoans_list().getSelectionModel().getSelectedItem().getId());
    }

    public Scene getScene() {
        return scene;
    }
}
