package Practice8;

public class ConstantsTest {
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
}
