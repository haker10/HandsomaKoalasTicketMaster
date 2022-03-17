package dal.dao;

import be.User;
import dal.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}

