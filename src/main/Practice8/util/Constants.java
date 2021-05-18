package main.Practice8.util;

public class Constants {
    private Constants(){}
    public static final String FIND_ALL_USERS = "SELECT id, login FROM users";
    public static final String INSERT_USER = "INSERT INTO users (login) VALUES (?)";
    public static final String FIND_ALL_TEAMS = "SELECT id, name FROM teams";
    public static final String INSERT_TEAM = "INSERT INTO teams (name) VALUES (?)";
    public static final String GET_USER = "SELECT id, login FROM users WHERE login = ?";
    public static final String GET_TEAM = "SELECT id, name FROM teams WHERE name = ?";
    public static final String GET_USER_TEAMS = "SELECT t.id, t.name FROM teams t " +
            "LEFT JOIN users_teams ut on t.id = ut.team_id WHERE ut.user_id = ?";
    public static final String DELETE_TEAM = "DELETE FROM teams WHERE id = ?";
    public static final String UPDATE_TEAM = "UPDATE teams SET name = ? WHERE id = ?";
    public static final String SET_TEAMS_FOR_USER = "INSERT INTO users_teams (user_id, team_id) VALUES(?, ?)";
}
