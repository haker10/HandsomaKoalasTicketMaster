package bll;

import be.User;
import dal.dao.UserDAO;

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
}
