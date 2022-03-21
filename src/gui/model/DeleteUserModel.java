package gui.model;

import be.User;
import bll.TicketMasterManager;
import dal.dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeleteUserModel {

    private ObservableList<User> allUsers;

    TicketMasterManager manager;
    UserDAO userDAO;

    public DeleteUserModel(){
        manager = new TicketMasterManager();
        userDAO = new UserDAO();
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
