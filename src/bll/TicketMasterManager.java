package bll;

import be.*;
import dal.dao.*;
import javafx.collections.ObservableList;

import java.util.Date;
import java.util.List;

public class TicketMasterManager {

    UserDAO userDAO;
    EventDAO eventDAO;
    CustomerDAO customerDAO;
    CustomerJoinsEventDAO customerJoinsEventDAO;
    TicketDAO ticketDAO;

    public TicketMasterManager(){
        userDAO = new UserDAO();
        eventDAO = new EventDAO();
        customerDAO = new CustomerDAO();
        customerJoinsEventDAO = new CustomerJoinsEventDAO();
        ticketDAO = new TicketDAO();
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

    public Event createEvent(String name, Date startDateAndTime, Date endDateAndTime, String address, String addressUrl, String ticketTypes, String additionalInfo) {
        return eventDAO.createEvent(name, startDateAndTime, endDateAndTime, address, addressUrl, ticketTypes, additionalInfo);
    }

    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }
    public List<Event> getAllEventsToDo() {
        return eventDAO.getAllEventsToDo();
    }

    public String getTicketTypes(int eventId) {
        return eventDAO.getTicketTypes(eventId);
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

    public Event editEvent(int id, String name, Date startDateAndTime, Date endDateAndTime, String address, String addressUrl, String ticketTypes, String additionalInfo) {
        return eventDAO.editEvent(id, name, startDateAndTime, endDateAndTime, address, addressUrl, ticketTypes, additionalInfo);
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

    public Ticket createTicket(int eventId, String customer, String choosenTypes) {

       return ticketDAO.createTicket(eventId, customer, choosenTypes);
    }

    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    public String getEventById(int id){ return eventDAO.getEventById(id); }

    public Event getEventByIdOL(int id){ return eventDAO.getEventByIdOL(id); }

    public String getCustomerByEmail(String email) { return customerDAO.getCustomerByEmail(email); }

    public List<Integer> getEventByCustomer(String customerEmail) {
        return customerJoinsEventDAO.getEventByCustomer(customerEmail);
    }

    public void deleteTicket(int id) {
        try{
            ticketDAO.deleteTicket(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
