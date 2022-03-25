package gui.model;

import be.User;
import bll.TicketMasterManager;


public class LoginModel {

    TicketMasterManager manager;

    public LoginModel(){
        manager = new TicketMasterManager();
    }


    public User login(String username, String password) {

        return manager.login(username, password);

    }
}
