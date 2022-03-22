package bll;

import be.Event;
import be.User;
import dal.dao.EventDAO;
import dal.dao.UserDAO;

import java.util.Date;
import java.util.List;

public class TicketMasterManager {

    UserDAO userDAO;
    EventDAO eventDAO;

    public TicketMasterManager(){
        userDAO = new UserDAO();
        eventDAO = new EventDAO();
    }

    public User login(String username, String password) {
        return userDAO.checkLogin(username, password);
    }

    public User createUser(String typeOfUser, String username, String password) {
        return userDAO.createUser(typeOfUser, username, password);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void deleteUser(int chosenUserId) {
        try{
            userDAO.deleteUser(chosenUserId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Event createEvent(String name, Date startDateAndTime, Date endDateAndTime, String address) {
        return eventDAO.createEvent(name, startDateAndTime, endDateAndTime, address);
    }
}
