package dal.dao;

import be.Customer;
import dal.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    DatabaseConnector databaseConnector;

    public CustomerDAO(){

        databaseConnector = new DatabaseConnector();

    }

    public Customer createCustomer(String name, String email, String phone) {

        Customer customer = getCustomerByEmail(email);

        if(customer != null)
            return customer;

        String sql = "INSERT INTO Customers(Name, Email, Phone) VALUES(?,?,?)";

        try(Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.executeUpdate();

            customer = new Customer(name, email, phone);

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return customer;
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        String sql = "SELECT * FROM Customers WHERE EMAIL=?";
        try (Connection connection = databaseConnector.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()){
                String name = resultSet.getString("NAME");
                String phone = resultSet.getString("PHONE");
                customer = new Customer(name, email, phone);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public List<Customer> getListOfParticipants(int eventId) {
        List<Customer> listOfParticipants = new ArrayList<>();
        String sql = "SELECT  c.NAME, c.EMAIL, c.PHONE FROM Customers as c INNER JOIN AddCustomerToEvent ON c.EMAIL=CustomerID WHERE EventID=?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, eventId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                Customer customer = new Customer(name, email, phone);
                listOfParticipants.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfParticipants;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        try (Connection connection = databaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String email = resultSet.getString("EMAIL");
                String phone = resultSet.getString("PHONE");
                Customer customer = new Customer(name, email, phone);
                allCustomers.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCustomers;
    }
}
