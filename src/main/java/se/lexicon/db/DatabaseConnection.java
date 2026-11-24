package se.lexicon.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3307/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {

    }

    private static DataSource mysqlDataSource;

    public static DataSource getMysqlDataSource() {
        if (mysqlDataSource == null) {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL(URL);
            ds.setUser(USER);
            ds.setPassword(PASSWORD);
            mysqlDataSource = ds;
        }
        return mysqlDataSource;
    }




}
