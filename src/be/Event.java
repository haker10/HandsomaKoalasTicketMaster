package be;

import java.awt.*;
import java.util.Date;
import java.io.IOException;
import java.net.URISyntaxException;

public class Event {
    int id;
    String name;
    Date startDatenTime;
    Date endDatenTime;
    String address;
    String addressUrl;
    String ticketTypes;
    String additionalInfo;

    public Event(int id, String name, Date startDatenTime, Date endDatenTime, String address, String ticketTypes, String additionalInfo) {
        this.id = id;
        this.name = name;
        this.startDatenTime = startDatenTime;
        this.endDatenTime = endDatenTime;
        this.address = address;
        this.ticketTypes = ticketTypes;
        this.additionalInfo = additionalInfo;
        this.addressUrl = addressUrl;
    }

    public void goUrl(String addressUrl) {
        if (java.awt.Desktop.isDesktopSupported()){
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(Desktop.Action.BROWSE)){
                try{
                    java.net.URI uri = new java.net.URI(addressUrl);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException exception){}
            }
        }
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Date getStartDatenTime(){
        return startDatenTime;
    }

    public Date getEndDatenTime(){
        return endDatenTime;
    }

    public String getAddress(){
        return address;
    }

    public String getTicketTypes() { return ticketTypes; }
    public String getAddressUrl() { return addressUrl; }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String toString(){
        return "Name: " + name + ", Start date: " + startDatenTime.toString().substring(0,10);
    }
}
