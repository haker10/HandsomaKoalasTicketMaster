package bll;

import be.User;
import dal.dao.UserDAO;

import java.sql.SQLException;

public class TicketMasterManager {
    UserDAO userDAO;

    public TicketMasterManager(){
        userDAO = new UserDAO();
    }

    public User login(String username, String password) {
        return userDAO.checkLogin(username, password);
    }
}
