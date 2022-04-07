package gui.model;

import bll.TicketMasterManager;

public class CreateNewUserModel {

    TicketMasterManager manager;

    public CreateNewUserModel(){
        manager = new TicketMasterManager();
    }

    public void createUser(String typeOfUser, String username, String password) {

        manager.createUser(typeOfUser, username, password);
    }

}
