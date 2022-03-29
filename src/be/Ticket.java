package be;

public class Ticket {
    int id;
    int eventId;
    String customerId;
    String ticketType;
    String additionalInfo;

    public int getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Ticket(int id, int eventId, String customerId, String ticketType, String additionalInfo){
        this.id = id;
        this.eventId = eventId;
        this.customerId = customerId;
        this.ticketType = ticketType;
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString(){
        return ("Event: " + eventId + ", Customer: " + customerId + ", Ticket Type: " + ticketType + ", Additional Info: " + additionalInfo);
    }
}
