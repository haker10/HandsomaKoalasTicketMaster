package gui.model;

import be.User;
import bll.TicketMasterManager;
import dal.dao.UserDAO;


public class LoginModel {

    TicketMasterManager manager;
    UserDAO userDAO;

    public LoginModel(){
        manager = new TicketMasterManager();
        userDAO = new UserDAO();
    }


    public User login(String username, String password) {

        return manager.login(username, password);

    }
}
