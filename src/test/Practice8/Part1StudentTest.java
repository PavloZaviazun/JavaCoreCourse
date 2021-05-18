package Practice8;

import main.Practice8.db.DBManager;
import main.Practice8.db.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class Part1StudentTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=youruser;password=yourpassword;";
    private static final String USER = "youruser";
    private static final String PASS = "yourpassword";
    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users (\n" +
            "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
            " login VARCHAR(20) NOT NULL, \n" +
            "  PRIMARY KEY (id));";

    private static DBManager dbManager;

    @BeforeClass
    public static void beforeTest() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        try (OutputStream outputStream = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(outputStream, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        dbManager = DBManager.getInstance();

        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_USERS);
        }
    }

    @Test
    public void insertUserAndGetAllTest() {
        User user1 = User.createUser("Pasha");
        User user2 = User.createUser("Dasha");
        dbManager.insertUser(user1);
        dbManager.insertUser(user2);
        List <User> allUsers = dbManager.findAllUsers();
        assertEquals("[Pasha, Dasha]", allUsers.toString());
    }

    @AfterClass
    public static void afterTest() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement()) {
            String sql = "DROP IF EXISTS TABLE users";
            statement.executeUpdate(sql);
        }
    }
}
