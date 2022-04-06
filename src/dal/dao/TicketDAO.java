package dal.dao;

import be.Event;
import be.Ticket;
import dal.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    DatabaseConnector databaseConnector;

    public TicketDAO() {

        databaseConnector = new DatabaseConnector();

    }

    public Ticket createTicket(int eventId, String customer, String choosenTypes) {
        Ticket ticket = null;
        String sql = "INSERT INTO Ticket(EventID, CustomerID, TicketType) VALUES(?,?,?)";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, eventId);
            preparedStatement.setString(2, customer);
            preparedStatement.setString(3, choosenTypes);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                ticket = new Ticket(id, eventId, customer, choosenTypes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> allTickets = new ArrayList<>();
        String sql = "SELECT * FROM Ticket";

        try (Connection connection = databaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int ticketId = resultSet.getInt("ID");
                int eventId = resultSet.getInt("EventID");
                String customerEmail = resultSet.getString("CustomerID");
                String ticketType = resultSet.getString("TicketType");
                Ticket ticket = new Ticket(ticketId, eventId, customerEmail, ticketType);
                allTickets.add(ticket);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allTickets;
    }
}

