package dal.dao;

import be.User;
import dal.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    DatabaseConnector databaseConnector;

    public UserDAO() {
        databaseConnector = new DatabaseConnector();
    }

    public User checkLogin(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE Username = ? and Password = ?";
        try (Connection connection = databaseConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                String typeOfUser = resultSet.getString("TypeOfUser");
                int id = resultSet.getInt("ID");
                user = new User(id, typeOfUser, username, password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User createUser(String typeOfUser, String username, String password){
        User user = null;
        String sql = "INSERT INTO Users(TypeOfUser, Username, Password) VALUES(?,?,?)";

        try(Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, typeOfUser);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                user = new User(id, typeOfUser, username, password);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection connection = databaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String typeOfUser = resultSet.getString("TypeOfUser");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                User user = new User(id, typeOfUser, username, password);
                allUsers.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allUsers;
    }

    public void deleteUser(int chosenUserId) {

        String sql = "DELETE FROM Users WHERE ID = ?";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chosenUserId);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

