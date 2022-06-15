package core.ann.classifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;

public class Dataset {
    private Matrix X_train;
    private Matrix X_test;
    private Matrix y_train;
    private Matrix y_test;

    private final static String X_train_path = "C:\\Users\\Liran\\OneDrive - ac.sce.ac.il\\JAVA CORE\\X_train.csv";
    private final static String X_test_path = "C:\\Users\\Liran\\OneDrive - ac.sce.ac.il\\JAVA CORE\\X_test.csv";
    private final static String y_train_path = "C:\\Users\\Liran\\OneDrive - ac.sce.ac.il\\JAVA CORE\\y_train.csv";
    private final static String y_test_path = "C:\\Users\\Liran\\OneDrive - ac.sce.ac.il\\JAVA CORE\\y_test.csv";

    private final static int X_train_rows = 230633;
    private final static int X_test_rows = 76878;
    private final static int y_train_rows = 230633;
    private final static int y_test_rows = 76878;

    private final static int X_attributes = 43;
    private final static int y_attributes = 1;

    public Dataset() throws MatrixIndexesOutOfBounds, IOException, InvalidMatrixDimension {
        this.X_train = loadData("X_train");
        this.X_test = loadData("X_test");
        this.y_train = loadData("y_train");
        this.y_test = loadData("y_test");
    }

    private static Matrix loadData(String type) throws IOException, InvalidMatrixDimension, MatrixIndexesOutOfBounds {
        BufferedReader buffer_reader;
        String current_line;
        Matrix temp;
        int row_cnt = 0;

        if (type.compareTo("X_train") == 0) {
            buffer_reader = new BufferedReader(new FileReader(X_train_path));
            temp = new Matrix(X_train_rows, X_attributes);
            buffer_reader.readLine();// IGNORE FIRST ROW

            while ((current_line = buffer_reader.readLine()) != null) {
                String[] row = current_line.split(",");    // use comma as separator
                for (int i = 1; i < row.length - 1; i++) {
                    double val = Double.parseDouble(row[i]);
                    temp.setValue(row_cnt, i - 1, val);
                }
                row_cnt++;
            }

        } else if (type.compareTo("X_test") == 0) {
            buffer_reader = new BufferedReader(new FileReader(X_test_path));
            temp = new Matrix(X_test_rows, X_attributes);
            buffer_reader.readLine(); // IGNORE FIRST ROW

            while ((current_line = buffer_reader.readLine()) != null) {
                String[] row = current_line.split(",");    // use comma as separator
                for (int i = 1; i < row.length - 1; i++) {
                    temp.setValue(row_cnt, i - 1, Double.parseDouble(row[i]));
                }
                row_cnt++;
            }

        } else if (type.compareTo("y_train") == 0) {
            buffer_reader = new BufferedReader(new FileReader(y_train_path));
            temp = new Matrix(y_train_rows, y_attributes);
            buffer_reader.readLine(); // IGNORE FIRST ROW

            while ((current_line = buffer_reader.readLine()) != null) {
                String[] row = current_line.split(",");    // use comma as separator
                temp.setValue(row_cnt, 0, Double.parseDouble(row[1]));
                row_cnt++;
            }
        } else if (type.compareTo("y_test") == 0) {
            buffer_reader = new BufferedReader(new FileReader(y_test_path));
            temp = new Matrix(y_test_rows, y_attributes);
            buffer_reader.readLine(); // IGNORE FIRST ROW

            while ((current_line = buffer_reader.readLine()) != null) {
                String[] row = current_line.split(",");    // use comma as separator
                temp.setValue(row_cnt, 0, Double.parseDouble(row[1]));
                row_cnt++;
            }
        } else
            throw new InvalidParameterException("type of data is invalid");

        return temp;
    }

    public Matrix X_train() {
        return this.X_train;
    }

    public Matrix X_test() {
        return this.X_test;
    }

    public Matrix y_train() throws MatrixIndexesOutOfBounds, InvalidMatrixDimension {
        return this.y_train.transpose();
    }

    public Matrix y_test() throws MatrixIndexesOutOfBounds, InvalidMatrixDimension {
        return this.y_test.transpose();
    }
}
