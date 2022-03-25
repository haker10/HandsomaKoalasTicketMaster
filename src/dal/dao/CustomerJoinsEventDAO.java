package dal.dao;

import be.CustomerJoinsEvent;
import dal.DatabaseConnector;

import java.sql.*;

public class CustomerJoinsEventDAO {

    DatabaseConnector databaseConnector;

    public CustomerJoinsEventDAO(){

        databaseConnector = new DatabaseConnector();

    }

    public CustomerJoinsEvent addCustomerToEvent(int eventId, String customerEmail) {

        CustomerJoinsEvent customerJoinsEvent = getCustomerInEvent(eventId, customerEmail);

        if(customerJoinsEvent != null)
            return customerJoinsEvent;

        String sql = "INSERT INTO AddCustomerToEvent(EventID, CustomerID) VALUES(?,?)";

        try(Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, customerEmail);
            preparedStatement.executeUpdate();

            customerJoinsEvent = new CustomerJoinsEvent(eventId, customerEmail);

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return customerJoinsEvent;

    }

    public CustomerJoinsEvent getCustomerInEvent(int eventId, String email) {

        CustomerJoinsEvent customerJoinsEvent = null;

        String sql = "SELECT * FROM AddCustomerToEvent WHERE EventID=? and CustomerID=?";

        try (Connection connection = databaseConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()){
                customerJoinsEvent = new CustomerJoinsEvent(eventId, email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerJoinsEvent;
    }
}
