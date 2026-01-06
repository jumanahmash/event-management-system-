package EventMangement;

import java.io.Serializable;

public class Organizer extends Person  {
    
    
    private String organizerId;
    private  Event organizedEvent; 

    public Organizer(String name, String email, String mobile, String organizerId) {
        super(name, email, mobile);
        this.organizerId = organizerId;
        this.organizedEvent = null;
    }

    public String getType() {
        return "Organizer";
    }

    public Event createEvent(String eventId, String eventName, String eventType, 
                           String date, String location) {
        if(organizedEvent == null) {
            organizedEvent = new Event(eventId, eventName, eventType, date, location, this);
            return organizedEvent;
        }
        return null;
    }

    public boolean hasEvent() {
        return organizedEvent != null;
    }

    public Event getOrganizedEvent() {
        return organizedEvent;
    }
    
    public void setOrganizedEvent(Event event) {
        this.organizedEvent = event;
    }

    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public void cancelEvent() {
        organizedEvent = null;
    }
    
    public String toString() {
        return super.toString() + ", Organizer ID: " + organizerId;
    }
    
    
}