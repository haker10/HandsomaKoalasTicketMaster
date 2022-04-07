package be;

public class Ticket {
    int id;
    int eventId;
    String customerId;
    String ticketType;

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

    public Ticket(int id, int eventId, String customerId, String ticketType){
        this.id = id;
        this.eventId = eventId;
        this.customerId = customerId;
        this.ticketType = ticketType;
    }

    @Override
    public String toString(){
        return ("Event: " + eventId + ", Customer: " + customerId + ", Ticket Type: " + ticketType);
    }
}
