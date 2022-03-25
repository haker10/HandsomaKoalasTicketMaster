package gui.model;

import be.User;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeleteUserModel {

    private ObservableList<User> allUsers;

    TicketMasterManager manager;

    public DeleteUserModel(){
        manager = new TicketMasterManager();
    }

    public ObservableList getAllUsers() {
        allUsers = FXCollections.observableArrayList();
        allUsers.addAll(manager.getAllUsers());
        return allUsers;
    }

    public void deleteUser(int chosenUserId) {
        manager.deleteUser(chosenUserId);
    }
}
