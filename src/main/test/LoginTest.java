
import com.example.demo.PostgreSQL;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;
import java.sql.SQLException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest {
    private static final PostgreSQL sql = PostgreSQL.getInstance();

    @BeforeClass
    public static void initSQL() {
        sql.openConnection();
    }
    @Test
    public void A1() {
        try {
            String[][] client_id, banker_id, manager_id;
            // Client Login
            client_id = sql.select("users", "id", String.format("username='%s', password='%s'", "yonatan", "1234"));

            // Banker Login
            banker_id = sql.select("users", "id", String.format("username='%s', password='%s'", "liransm", "PM2022"));

            // Manager Login
            manager_id = sql.select("users", "id", String.format("username='%s', password='%s'", "tamaram", "PM2022"));

            if (client_id[0].length > 0)
                System.out.println("Client Found, ID: " + client_id[0][0]);

            if (banker_id[0].length > 0)
                System.out.println("Banker Found, ID: " + banker_id[0][0]);

            if (manager_id[0].length > 0)
                System.out.println("Manager Found, ID: " + manager_id[0][0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
