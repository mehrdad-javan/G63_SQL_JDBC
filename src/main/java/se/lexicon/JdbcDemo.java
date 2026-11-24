package se.lexicon;

import java.sql.*;
import java.time.LocalDateTime;

public class JdbcDemo {

    private static final String URL = "jdbc:mysql://localhost:3307/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static void main() {

        ex2();

    }

    public static void ex1() {

        try (
                // Connection Interface is used to establish a connection to the database.
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
        ) {

            String query = "SELECT id, name, class_group, create_date FROM student";
            ResultSet resultSet = statement.executeQuery(query);
            // executeQuery: ✅ Use Case: Fetching records from the database. SELECT
            // executeUpdate: ✅ Use Case: Modifying data (returns number of affected rows) INSERT, UPDATE, DELETE
            // execute: ✅ Use Case: Running stored procedures or unknown query types.
            // executeBatch: ✅ Use Case: Bulk inserting multiple rows efficiently.

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String classGroup = resultSet.getString("class_group");
                LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();
                IO.println("ID: " + id + " | Name: " + name + " | Class: " + classGroup + " | Created At: " + createDate);
            }

        } catch (SQLException e) {
            IO.println("❌ Error connecting to the database: " + e.getMessage());
        }

    }

    public static void ex2() {
        String query = "SELECT id, name, class_group, create_date FROM student WHERE class_group LIKE ?";
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(query);

        ) {

            preparedStatement.setString(1, "%G1%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String classGroup = resultSet.getString("class_group");
                LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();
                IO.println("ID: " + id + " | Name: " + name + " | Class: " + classGroup + " | Created At: " + createDate);
            }
        } catch (SQLException e) {
            IO.println("❌ Error connecting to the database: " + e.getMessage());
        }
    }
}
