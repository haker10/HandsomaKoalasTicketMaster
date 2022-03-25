package gui.model;

import be.Customer;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SearchCustomersModel {

    TicketMasterManager manager;

    private ObservableList<Customer> allCustomers;


    public SearchCustomersModel(){

        manager = new TicketMasterManager();

    }

    public ObservableList getAllCustomers() {
        allCustomers = FXCollections.observableArrayList();
        allCustomers.addAll(manager.getAllCustomers());
        return allCustomers;
    }
}
