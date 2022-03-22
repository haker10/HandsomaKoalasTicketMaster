package dal.dao;

import be.Event;
import be.User;
import dal.DatabaseConnector;

import java.sql.*;
import java.util.Date;

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

}
