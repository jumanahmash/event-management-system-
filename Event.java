package EventMangement;

import java.io.Serializable;

public class Event implements Serializable {
  
    
    private String eventId;
    private String eventName;
    private String eventType;
    private String date;
    private String location;
    private  Organizer organizer; 
    private PLinkedList<Person> attendees;

    public Event(String eventId, String eventName, String eventType, 
                 String date, String location, Organizer organizer) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.date = date;
        this.location = location;
        this.organizer = organizer; 
        this.attendees = new PLinkedList<>();
    }

    public void addAttendee(Person person) {
        attendees.insertAtFront(person);
    }

    public void removeAttendee(Person person) {
        attendees.remove(person);
    }

    public PLinkedList<Person> getAttendees() {
        return attendees;
    }

    public void setAttendees(PLinkedList<Person> attendees) {
        this.attendees = attendees;
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public String getEventType() {
        return eventType;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getLocation() {
        return location;
    }
    
    public Organizer getOrganizer() {
        return organizer;
    }
    
    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String toString() {
        String organizerName = (organizer != null) ? organizer.getName() : "No Organizer";
        return "Event ID: " + eventId + ", Name: " + eventName + 
               ", Type: " + eventType + ", Date: " + date + 
               ", Location: " + location + ", Organizer: " + organizerName;
    }
}