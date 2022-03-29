package bll;

import be.Customer;
import be.CustomerJoinsEvent;
import be.Event;
import be.User;
import dal.dao.CustomerDAO;
import dal.dao.CustomerJoinsEventDAO;
import dal.dao.EventDAO;
import dal.dao.UserDAO;

import java.util.Date;
import java.util.List;

public class TicketMasterManager {

    UserDAO userDAO;
    EventDAO eventDAO;
    CustomerDAO customerDAO;
    CustomerJoinsEventDAO customerJoinsEventDAO;

    public TicketMasterManager(){
        userDAO = new UserDAO();
        eventDAO = new EventDAO();
        customerDAO = new CustomerDAO();
        customerJoinsEventDAO = new CustomerJoinsEventDAO();
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

    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    public void deleteEvent(int chosenEventId) {
        try{
            eventDAO.deleteEvent(chosenEventId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Customer createCustomer(String name, String email, String phone) {
        return customerDAO.createCustomer(name, email, phone);
    }

    public CustomerJoinsEvent addCustomerToEvent(int eventId, String customerEmail) {
        return customerJoinsEventDAO.addCustomerToEvent(eventId, customerEmail);
    }

    public Event editEvent(int id, String name, Date startDateAndTime, Date endDateAndTime, String address) {
        return eventDAO.editEvent(id, name, startDateAndTime, endDateAndTime, address);
    }

    public List<Customer> getListOfParticipants(int eventId) {
        return customerDAO.getListOfParticipants(eventId);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public void deleteCustomer(String email) {
        try{
            customerDAO.deleteCustomer(email);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
