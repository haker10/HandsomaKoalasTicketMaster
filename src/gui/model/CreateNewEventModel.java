package gui.model;

import bll.TicketMasterManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class CreateNewEventModel {

    private ObservableList<String> listTypes;

    TicketMasterManager manager;

    public CreateNewEventModel(){

        manager = new TicketMasterManager();
    }

    public void createEvent(String name, Date startDateAndTime, Date endDateAndTime, String address, String addressUrl, String ticketTypes, String additionalInfo) {
        manager.createEvent(name, startDateAndTime, endDateAndTime, address, addressUrl, ticketTypes, additionalInfo);
    }

    public ObservableList ticketTypes(String types) {
        types.replaceAll("\\s+","");
        String str[] = types.split(",");

        listTypes = FXCollections.observableArrayList();
        listTypes.addAll(str);
        return listTypes;
    }
}
