package gui.model;

import be.Customer;
import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeleteCustomerModel {

    TicketMasterManager manager;

    private ObservableList<Customer> allCustomers;

    public DeleteCustomerModel(){

        manager = new TicketMasterManager();

    }

    public ObservableList getAllCustomers() {
        allCustomers = FXCollections.observableArrayList();
        allCustomers.addAll(manager.getAllCustomers());
        return allCustomers;
    }

    public void deleteCustomer(String email) {

        manager.deleteCustomer(email);

    }
}
