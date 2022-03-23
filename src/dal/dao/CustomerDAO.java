package dal.dao;

import dal.DatabaseConnector;

public class CustomerDAO {

    DatabaseConnector databaseConnector;

    public CustomerDAO(){

        databaseConnector = new DatabaseConnector();

    }
}
