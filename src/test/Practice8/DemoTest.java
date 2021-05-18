package Practice8;

import main.Practice8.Demo;
import main.Practice8.db.DBManager;
import main.Practice8.db.entity.Team;
import main.Practice8.db.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class DemoTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=youruser;password=yourpassword;";
    private static final String USER = "youruser";
    private static final String PASS = "yourpassword";
    private static final String SEP = System.lineSeparator();
    private static final String RESULT = "[ivanov, obama, petrov]" + SEP +
            "===========================" + SEP +
            "[teamA, teamB, teamC]" + SEP +
            "===========================" + SEP +
            "[teamA]" + SEP +
            "~~~~~" + SEP +
            "[teamA, teamB, teamC]" + SEP +
            "~~~~~" + SEP +
            "[teamA, teamB]" + SEP +
            "~~~~~" + SEP +
            "===========================" + SEP +
            "[teamB, teamX]" + SEP;
    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS users (\n" +
            "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
            " login VARCHAR(20) NOT NULL, \n" +
            "  PRIMARY KEY (id));";
    public static final String CREATE_TABLE_TEAMS = "CREATE TABLE IF NOT EXISTS teams (\n" +
            "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
            " name VARCHAR(20) NOT NULL, \n" +
            "  PRIMARY KEY (id));";
    public static final String CREATE_TABLE_USERS_TEAMS = "CREATE TABLE IF NOT EXISTS users_teams (" +
            " user_id INTEGER(11) NOT NULL," +
            " team_id INTEGER(11) NOT NULL," +
            " CONSTRAINT USERS_TEAMS_TEAMS_ID_FK" +
            " FOREIGN KEY (team_id)" +
            " REFERENCES teams (id) ON DELETE CASCADE," +
            " CONSTRAINT USERS_TEAMS_USERS_ID_FK" +
            " FOREIGN KEY (user_id)" +
            " REFERENCES users (id) ON DELETE CASCADE);";

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
            statement.executeUpdate(CREATE_TABLE_TEAMS);
            statement.executeUpdate(CREATE_TABLE_USERS_TEAMS);
        }
    }

    @Test
    public void mainDemoTest() {
        dbManager.insertUser(User.createUser("ivanov"));
        dbManager.insertTeam(Team.createTeam("teamA"));
        PrintStream initialOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        Demo.main(null);
        assertEquals(RESULT, baos.toString());
        System.setOut(initialOut);
    }

    @AfterClass
    public static void afterTest() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement()) {
            String sql1 = "DROP IF EXISTS TABLE teams";
            String sql2 = "DROP IF EXISTS TABLE teams";
            String sql3 = "DROP IF EXISTS TABLE teams";
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
        }
    }
}
