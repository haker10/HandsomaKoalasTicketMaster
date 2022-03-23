package dal.dao;

import be.Event;
import be.User;
import dal.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDAO {

    DatabaseConnector databaseConnector;

    public EventDAO(){
        databaseConnector = new DatabaseConnector();
    }

    public Event createEvent(String name, Date startDateAndTime, Date endDateAndTime, String address) {
        Event event = null;
        java.sql.Timestamp startDateAndTimeSql = new java.sql.Timestamp(startDateAndTime.getTime());
        java.sql.Timestamp endDateAndTimeSql = new java.sql.Timestamp(endDateAndTime.getTime());
        String sql = "INSERT INTO Events(NAME, STARTDATENTIME, ENDDATENTIME, ADDRESS) VALUES(?,?,?,?)";

        try(Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setTimestamp(2, startDateAndTimeSql);
            preparedStatement.setTimestamp(3, endDateAndTimeSql);
            preparedStatement.setString(4, address);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                event = new Event(id, name, startDateAndTime, endDateAndTime, address);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return event;
    }

    public List<Event> getAllEvents() {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection connection = databaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                Date startDateAndTime = resultSet.getTimestamp("STARTDATENTIME");
                Date endDateAndTime = resultSet.getTimestamp("ENDDATENTIME");
                String address = resultSet.getString("ADDRESS");
                Event event = new Event(id, name, startDateAndTime, endDateAndTime, address);
                allEvents.add(event);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allEvents;
    }

    public void deleteEvent(int chosenEventId) {
        String sql = "DELETE FROM Events WHERE ID = ?";

        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chosenEventId);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
