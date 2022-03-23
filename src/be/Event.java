package be;


import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Date;

public class Event {
    int id;
    String name;
    Date startDatenTime;
    Date endDatenTime;
    String address;

    //Constructor
    public Event(int id, String name, Date startDatenTime, Date endDatenTime, String address){
        this.id = id;
        this.name = name;
        this.startDatenTime = startDatenTime;
        this.endDatenTime = endDatenTime;
        this.address = address;
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


    public String toString(){
        return "Name: " + name + ", Start date: " + startDatenTime.toString().substring(0,10);
    }
}
