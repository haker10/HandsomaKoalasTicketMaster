package gui.model;

import be.User;
import bll.TicketMasterManager;
import dal.dao.UserDAO;

public class CreateNewUserModel {

    TicketMasterManager manager;
    UserDAO userDAO;

    public CreateNewUserModel(){
        manager = new TicketMasterManager();
        userDAO = new UserDAO();
    }

    public void createUser(String typeOfUser, String username, String password) {
        User user = manager.createUser(typeOfUser, username, password);
    }
}
