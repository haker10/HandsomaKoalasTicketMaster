package dal.dao;

import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.DatabaseConnector;

import java.sql.*;

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
}

