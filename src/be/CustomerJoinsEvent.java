package be;

public class CustomerJoinsEvent {
    int eventID;
    String customerID;

    public CustomerJoinsEvent(int eventID, String customerID){
        this.eventID = eventID;
        this.customerID = customerID;
    }

    public int getEventID(){
        return eventID;
    }

    public String getCustomerID(){
        return customerID;
    }

    public String toString(){
        return eventID + " " + customerID;
    }
}
