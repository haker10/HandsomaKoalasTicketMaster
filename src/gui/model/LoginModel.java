package gui.model;

import be.User;
import bll.TicketMasterManager;
import dal.dao.UserDAO;

import java.sql.SQLException;

public class LoginModel {

    TicketMasterManager manager;
    UserDAO userDAO;

    private User user;

    public LoginModel(){
        manager = new TicketMasterManager();
        userDAO = new UserDAO();
    }


    public User login(String username, String password) {

        return manager.login(username, password);

    }
}
