package Practice8;

import main.Practice8.db.DBManager;
import main.Practice8.db.entity.Team;
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
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class Part5StudentTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=youruser;password=yourpassword;";
    private static final String USER = "youruser";
    private static final String PASS = "yourpassword";
    public static final String CREATE_TABLE_TEAMS = "CREATE TABLE IF NOT EXISTS teams (\n" +
            "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
            " name VARCHAR(20) NOT NULL, \n" +
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
            statement.executeUpdate(CREATE_TABLE_TEAMS);
        }
    }

    @Test
    public void insertUserAndGetAllTest() {
        Team team1 = Team.createTeam("team1");
        Team team2 = Team.createTeam("team2");
        dbManager.insertTeam(team1);
        dbManager.insertTeam(team2);
        Team teamA = dbManager.getTeam("team1");
        teamA.setName("teamA");
        dbManager.updateTeam(teamA);
        Team teamARecieved = dbManager.getTeam("teamA");
        int id = teamARecieved.getId();
        assertEquals(1, id);

    }

    @AfterClass
    public static void afterTest() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = connection.createStatement()) {
            String sql = "DROP IF EXISTS TABLE teams";
            statement.executeUpdate(sql);
        }
    }
}
