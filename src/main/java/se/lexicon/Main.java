package se.lexicon;

import se.lexicon.dao.StudentDao;
import se.lexicon.dao.StudentDaoImpl;
import se.lexicon.db.DatabaseConnection;
import se.lexicon.model.Student;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    static void main() {
        DataSource dataSource = DatabaseConnection.getMysqlDataSource();
        try (Connection connection = dataSource.getConnection()) {


            try {

                connection.setAutoCommit(false);

                StudentDao studentDao = new StudentDaoImpl(connection);

                Student student = new Student(
                        "Mehrdad Javan",
                        "Java-Group-1"
                );

                Student savedStudent = studentDao.save(student);
                System.out.println("Saved student: " + savedStudent);

                List<Student> students = studentDao.findAll();
                students.forEach(System.out::println);

                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
