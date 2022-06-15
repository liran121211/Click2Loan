package com.example.demo;

import java.sql.*;
import java.util.HashMap;


public class PostgreSQL {
    private Connection connection;
    private ResultSet cursor;
    private Statement stmt;
    private HashMap<Integer, String> notifications;

    private final static String SERVER_IP = "207.154.201.63";
    private final static String SERVER_PORT = "5432";
    private final static String DB_NAME = "PM2022_TEAM_9";
    private final static String DB_USER = "liransm";
    private final static String DB_PASSWORD = "PM2022";
    private static volatile PostgreSQL instance = null;

    /**
     * Double-Lock Thread Safe Singleton
     *
     * @return (PostgreSQL) object.
     */
    public static PostgreSQL getInstance() {
        if (instance == null) {
            synchronized (PostgreSQL.class) {
                if (instance == null)
                    instance = new PostgreSQL();
            }
        }
        return instance;
    }

    private PostgreSQL() { // Default Constructor
        Notifications();
    }

    /**
     * Open connection to the Database.
     */
    public void openConnection() {
        String URL_BUILDER = "jdbc:postgresql://%s:%s/%s".formatted(SERVER_IP, SERVER_PORT, DB_NAME);
        try {
            this.connection = DriverManager.getConnection(URL_BUILDER, DB_USER, DB_PASSWORD);
            //dispatchStatus(0);
        } catch (SQLException e) {
            dispatchStatus(2);
            System.exit(2);
        }
    }

    /**
     * Close connection to the database
     * closing order: statement->cursor->connection
     */
    public void closeConnection() {
        try {
            System.out.println("Connection closed!");

        } finally {
            if (this.stmt != null) {
                try {
                    this.stmt.close();
                } catch (SQLException e) {
                    dispatchStatus(3);
                }
            }
            if (this.cursor != null) {
                try {
                    this.cursor.close();
                } catch (SQLException e) {
                    dispatchStatus(3);
                }
            }
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    dispatchStatus(3);
                }
            }
        }
        dispatchStatus(1);
    }

    public void insert(String table, String values) {
        try {
            String query = "INSERT INTO %s VALUES (%s);".formatted(table, values);
            this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            this.stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            e.printStackTrace();
            dispatchStatus(5);
            System.exit(5);
        }
    }

    public void insert(String table, String columns, String values) {
        try {
            String query = "INSERT INTO %s(%s) VALUES (%s)".formatted(table, columns, values);
            this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            this.stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            e.printStackTrace();
            dispatchStatus(5);
            System.exit(5);
        }
    }

    /**
     * Extract data from specific table.
     *
     * @param table   (String) name
     * @param columns (String) columns name (separated by comma).
     * @return String[][] array.
     */
    public String[][] select(String table, String columns) throws SQLException {
        try {
            String query = "SELECT %s FROM %s".formatted(columns, table);
            this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.cursor = stmt.executeQuery(query);
        } catch (SQLException e) {
            dispatchStatus(4);
//            System.exit(4);
        }
        return formatQuery(this.cursor);
    }

    /**
     * Extract data from specific table.
     *
     * @param table     (String) name
     * @param columns   (String) columns name (separated by comma).
     * @param condition (String) equal to WHERE in SQL.
     * @return String[][] array.
     */
    public String[][] select(String table, String columns, String condition) throws SQLException {
        try {
            String split_condition = condition.replace(",", " AND ");
            String query = "SELECT %s FROM %s WHERE %s".formatted(columns, table, split_condition);
            this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.cursor = stmt.executeQuery(query);
        } catch (SQLException e) {
            dispatchStatus(4);
            e.printStackTrace();
//            System.exit(4);
        }
        return formatQuery(this.cursor);
    }

    /**
     * Update specific data of specific table.
     *
     * @param table   (String) name
     * @param columns (String) columns name (separated by comma).
     */
    public boolean update(String table, String columns, String values) {
        try {
            StringBuilder update_clause = new StringBuilder();
            String[] split_columns = columns.split(",");
            String[] split_values = values.split(",");

            for (int i = 0; i < split_columns.length; i++) {
                if (i != split_columns.length - 1)
                    update_clause.append(String.format("%s = '%s' ,", split_columns[i], split_values[i]));
                else
                    update_clause.append(String.format("%s = '%s'", split_columns[i], split_values[i]));
            }

            String query = "UPDATE %s SET %s".formatted(table, update_clause);
            this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            dispatchStatus(6);
//            System.exit(6);
        }
        return false;
    }

    /**
     * Update specific data of specific table.
     *
     * @param table     (String) name
     * @param columns   (String) columns name (separated by comma).
     * @param condition (String) equal to WHERE in SQL.
     */
    public void update(String table, String columns, String values, String condition) {
        try {
            String split_condition = condition.replace(",", " AND ");
            StringBuilder update_clause = new StringBuilder();
            String[] split_columns = columns.split(",");
            String[] split_values = values.split(",");

            for (int i = 0; i < split_columns.length; i++) {
                if (i != split_columns.length - 1)
                    update_clause.append(String.format("%s = '%s' ,", split_columns[i], split_values[i]));
                else
                    update_clause.append(String.format("%s = '%s'", split_columns[i], split_values[i]));
            }

            String query = "UPDATE %s SET %s WHERE %s".formatted(table, update_clause, split_condition);
            this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
            dispatchStatus(6);
            System.exit(6);
        }
    }

    /**
     * Update specific data of specific table.
     *
     * @param table     (String) name
     * @param condition (String) equal to WHERE in SQL.
     */
    public void delete(String table, String condition) {
        try {
            String query = "DELETE FROM %s WHERE %s".formatted(table, condition);
            this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            dispatchStatus(7);
//            System.exit(7);
        }
    }

    /**
     * Format sql query result to String[][] array.
     *
     * @param query (ResultSet) object.
     * @return String[][] array.
     */
    private String[][] formatQuery(ResultSet query) throws SQLException {
        if (query == null)
            System.exit(-1);

        ResultSetMetaData meta_data = query.getMetaData();
        int n_columns = meta_data.getColumnCount();
        int n_rows = get_rows(query);
        String[][] temp = new String[n_rows][n_columns];

        while (query.next()) { // iterate over all fetched rows.
            for (int i = 0; i < n_columns; i++)
                temp[n_rows - 1][i] = this.cursor.getString(i + 1);
            n_rows--;
        }
        return temp;
    }

    /**
     * Get (n) rows of the current fetched data.
     *
     * @param query (ResultSet) object
     * @return number of rows.
     */
    private int get_rows(ResultSet query) {
        int total_rows = 0;
        try {
            query.last();
            total_rows = query.getRow();
            query.beforeFirst();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return total_rows;
    }

    /**
     * dispatch terminal notification for specific (code) message.
     *
     * @param code (int)
     */
    private void dispatchStatus(int code) {
        System.out.println(this.notifications.get(code));
    }

    /**
     * Manage Hash Table of all notifications related to SQL.
     */
    private void Notifications() {
        this.notifications = new HashMap<>();
        this.notifications.put(0, "Database connection established successfully");
        this.notifications.put(1, "Database connection closed successfully");
        this.notifications.put(2, "An error occurred while connecting to a database");
        this.notifications.put(3, "An error occurred while closing the connection to the database");
        this.notifications.put(4, "An error occurred, please check the parameters sent to select() function");
        this.notifications.put(5, "An error occurred, please check the parameters sent to insert() function");
        this.notifications.put(6, "An error occurred, please check the parameters sent to update() function");
        this.notifications.put(7, "An error occurred, please check the parameters sent to delete() function");
    }

    /**
     * Print Table
     *
     * @param arr (String[][]) Table.
     */
    public static void prettyPrint(String[][] arr) {
        if (arr.length == 0)
            System.out.println("Nothing to print...");

        for (String[] strings : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(strings[j] + " | ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws SQLException {
        PostgreSQL sql = new PostgreSQL();
        sql.openConnection();
        // TABLE SCHEME: personid | lastname | firstname | address | city
        //prettyPrint(sql.select("persons", "*")); // Print all data of table [persons]


        //sql.insert("persons", "16, 'LASTNAME16', 'FIRSTNAME16', 'ADDRESS16', 'CITY16'"); // table | (col1, col2, col3....)
        //prettyPrint(sql.select("persons", "*"));

        //sql.update("persons", "lastname, firstname, address, city", "1, 2, 3, 4"); // table | col1=value1, col2=value2, col3=value3....
        //prettyPrint(sql.select("persons", "*"));

        //sql.update("persons", "lastname, firstname, address, city", "1, 2, 3, 4", "personid=16"); // table | col1=value1, col2=value2, col3=value3 WHERE .....
        //prettyPrint(sql.select("persons", "*"));

        //sql.delete("persons", "personid=16");
        //prettyPrint(sql.selec t("persons", "*"));

        sql.closeConnection();
    }
}




