package bll;

import be.User;
import dal.dao.UserDAO;

import java.util.List;

public class TicketMasterManager {

    UserDAO userDAO;

    public TicketMasterManager(){
        userDAO = new UserDAO();
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
}
