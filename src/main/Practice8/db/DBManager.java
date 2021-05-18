package main.Practice8.db;

import main.Practice8.db.entity.Team;
import main.Practice8.db.entity.User;
import main.Practice8.util.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {

    private static DBManager dbManager;
    private Connection connection;

    private DBManager() {
        Properties properties = new Properties();
        try(FileInputStream isr = new FileInputStream("app.properties")) {
            properties.load(isr);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String property = properties.getProperty("connection.url");
        try {
            connection = getConnection(property);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized DBManager getInstance() {
        if(dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    public List<User> findAllUsers() {
        List <User> list = new ArrayList <>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.FIND_ALL_USERS);
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
        }
        return list;
    }

    public void insertUser(User user) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(Constants.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            int i = statement.executeUpdate();
            if(i > 0) {
                resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    public void close(AutoCloseable el) {
        try {
            if(el != null) el.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Team> findAllTeams() {
        List<Team> list = new ArrayList <>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(Constants.FIND_ALL_TEAMS);
            while(resultSet.next()) {
                Team team = new Team();
                team.setId(resultSet.getInt(1));
                team.setName(resultSet.getString(2));
                list.add(team);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
        }
        return list;
    }

    public void insertTeam(Team team) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(Constants.INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, team.getName());
            int i = statement.executeUpdate();
            if(i > 0) {
                resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    team.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
        }
    }

    public User getUser(String login) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            statement = connection.prepareStatement(Constants.GET_USER);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
        }
        return user;
    }

    public Team getTeam(String name) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Team team = new Team();
        try {
            statement = connection.prepareStatement(Constants.GET_TEAM);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                team.setId(resultSet.getInt(1));
                team.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
        }
        return team;
    }

    public List<Team> getUserTeams(User user) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Team> list = new ArrayList <>();
        try {
            statement = connection.prepareStatement(Constants.GET_USER_TEAMS);
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Team team = new Team();
                team.setId(resultSet.getInt(1));
                team.setName(resultSet.getString(2));
                list.add(team);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(resultSet);
            close(statement);
        }
        return list;
    }

    public void deleteTeam(Team team) {
        try (PreparedStatement statement = connection.prepareStatement(Constants.DELETE_TEAM)) {
            statement.setInt(1, team.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTeam(Team team) {
        try (PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_TEAM)) {
            statement.setString(1, team.getName());
            statement.setInt(2, team.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setTeamsForUser(User user, Team ... teams) {
        try(PreparedStatement statement = connection.prepareStatement(Constants.SET_TEAMS_FOR_USER)) {
            connection.setAutoCommit(false);
            for(Team team : teams) {
                statement.setInt(1, user.getId());
                statement.setInt(2, team.getId());
                statement.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}

