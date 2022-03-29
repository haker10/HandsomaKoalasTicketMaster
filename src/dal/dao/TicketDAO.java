package dal.dao;

import dal.DatabaseConnector;

public class TicketDAO {

    DatabaseConnector databaseConnector;

    public TicketDAO(){

        databaseConnector = new DatabaseConnector();

    }
}
