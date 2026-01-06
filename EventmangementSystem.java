/*package EventMangement;


import java.util.*;
public class EventmangementSystem {
static Scanner input = new Scanner(System.in);
static PLinkedList<Person> allPeople = new PLinkedList<>();//we use generic linked list to be able to store all people like Organizer, Participant, and VIPParticipant


static Organizer currentOrganizer;

public static void main(String[] args) {
System.out.println("=== Organizer Registration ===");
System.out.print("Enter organizer name: ");
String name = input.nextLine();

System.out.print("Enter organizer email: ");
String email = input.nextLine();

System.out.print("Enter organizer mobile: ");
String mobile = input.nextLine();

System.out.print("Enter organizer ID: ");
String id = input.nextLine();

currentOrganizer = new Organizer(name, email, mobile, id);
allPeople.insertAtFront(currentOrganizer);

System.out.println("Welcome, " + name + "! (ID: " + id + ")");

allPeople.insertAtFront(new Participant("Sara", "sara@email.com", "0552222222", "P001")); //Sample participants data created;
allPeople.insertAtFront(new Participant("Khalid", "khalid@email.com", "0553333333", "P002"));
allPeople.insertAtFront( new VIPParticipant("Nora", "nora@email.com", "0554444444", "VIP001", "GOLD"));
allPeople.insertAtFront( new VIPParticipant("Mohammed", "mohammed@email.com", "0555555555", "VIP002", "PLATINUM"));


int choice;
do {
System.out.println("***** Event Organizer System *****");
System.out.println("1- Create New Event");
System.out.println("2- Add Attendee to Event");
System.out.println("3- Display Attendees by Type");
System.out.println("4- Remove Attendee from Event");
System.out.println("5- Cancel Event");
System.out.println("6- Exit");
System.out.print("Enter your choice: ");
choice = input.nextInt();
input.nextLine();

switch(choice) {
case 1:
createNewEvent();
break;
case 2:
addAttendee();
break;
case 3:
displayAttendeesByType();
break;
case 4:
removeAttendee();
break;
case 5:
cancelEvent();
break;
case 6:
System.out.println("Goodbye");
break;
default:
System.out.println("Invalid choice!");
}
} while(choice != 6);


}

public static void createNewEvent() {
if(currentOrganizer.hasEvent()) {
System.out.println("You already have an active event!");
System.out.println("Current event: " + currentOrganizer.getOrganizedEvent().getEventName());
return;
}

System.out.println("Create New Event");
System.out.print("Enter event name: ");
String name = input.nextLine();

System.out.print("Enter event type: ");
String type = input.nextLine();

System.out.print("Enter event date: ");
String date = input.nextLine();

System.out.print("Enter event location: ");
String location = input.nextLine();

String eventId = "EVT-" + currentOrganizer.getOrganizerId();

Event event = currentOrganizer.createEvent(eventId, name, type, date, location);// we create event inside the organizer class //composition relation

if(event != null) {
System.out.println("Event created successfully: " + name);
System.out.println("Event Details: " + event.toString());
} else {
System.out.println("Failed to create event!");
}
}

public static void addAttendee() {
if(!currentOrganizer.hasEvent()) {
System.out.println("No active event found! Please create an event first.");
return;
}

Event event = currentOrganizer.getOrganizedEvent();

System.out.println("Add Attendee");
System.out.println("Available People:");

// Start from the beginning of the main list (Head)
Node<Person> current= allPeople.getHead();
boolean foundAny=false;

// Loop through the linked list until we reach the end (null)
while(current != null){
 // Get the person data from the current node
Person p=current.getData();
if(p instanceof Participant && ! p.equals(currentOrganizer)) {
System.out.println(" - " + p.getName() + " (" + p.getType() + ")" + "- " + p.getEmail());
foundAny=true;
}
// Move the pointer to the next person in the list
current = current.getNext();
}
// If the list was empty or only had the organizer we stop here
if(!foundAny) {
System.out.println("No available participants");
return;
}
System.out.print("Enter person name to add: ");
String personName = input.nextLine();

Person foundPerson=null; // To store the person if we find them
// Reset the pointer to the Head to start searching from the beginning again
current= allPeople.getHead();


while (current != null) {
Person p = current.getData();
if (p.getName().equals(personName) && p instanceof Participant) {
foundPerson = p; // We found the exact object in memory
break; 
}
// Move to next node
current = current.getNext();
}
if (foundPerson != null) {
event.addAttendee(foundPerson);
System.out.println("Successfully added: " + foundPerson.getName()+ " to the event");
} else {
System.out.println("Name not found! Please check spelling.");
}
}

public static void displayAttendeesByType() {
if(!currentOrganizer.hasEvent()) {
System.out.println("No active event found! Please create an event first.");
return;
}

Event event = currentOrganizer.getOrganizedEvent();
//Get the Linked List specifically for this event
PLinkedList<Person> attendees = event.getAttendees();

// Prepare the pointer at the start of the list
Node<Person> current = attendees.getHead(); 
System.out.println("Display Attendees");
System.out.print("Show (V: VIP, R: Regular, A: All): ");
char displayType = input.next().charAt(0);
input.nextLine(); 

boolean found = false;  // Flag to track if we printed anything

switch(Character.toUpperCase(displayType)) {
case 'V':
System.out.println("VIP Attendees");

while (current != null) {
Person p = current.getData();

if(p instanceof VIPParticipant) {
VIPParticipant vip = (VIPParticipant) p;  // Downcast 'Person' to 'VIPParticipant' to access getVipLevel()

System.out.println("- " + vip.getName() + " (" + vip.getVipLevel() + " VIP) - " + vip.getEmail());
found = true;
}
current = current.getNext(); // Move next
}
if(!found) System.out.println("No VIP attendees found.");
break;

case 'R':
System.out.println("Regular Attendees");
while (current != null) {
Person p = current.getData();

if(p instanceof Participant && !(p instanceof VIPParticipant)) {

System.out.println("- " + p.getName() + " - " + p.getEmail());
found = true;
}
current = current.getNext(); // Move next
}
if(!found) System.out.println("No regular attendees found.");
break;

case 'A':
System.out.println("All Attendees");
while (current != null) {
Person p = current.getData();
// Print using generic Person methods
System.out.println("- " + p.getName() + " - " + p.getType() + " - " + p.getEmail());
found = true;
current = current.getNext(); // Move next
}
if(!found) System.out.println("No attendees found.");
break;

default:
System.out.println("Invalid display type!");
}
}
public static void removeAttendee() {
if (!currentOrganizer.hasEvent()) {
System.out.println("No active event!");
return;
}

Event event = currentOrganizer.getOrganizedEvent();
PLinkedList<Person> attendees = event.getAttendees();

if (attendees.isEmpty()) {
System.out.println("List is empty.");
return;
}


System.out.println("Remove Attendee");
System.out.println("Current Attendees:");
attendees.print();
System.out.print("Enter name to remove: ");
String personName = input.nextLine();


Person foundPerson = null;
Node<Person> current = attendees.getHead();

while (current != null) {

if (current.getData().getName().equals(personName)) {
foundPerson = current.getData();
break; 
}
current = current.getNext();
}


if (foundPerson != null) {

event.removeAttendee(foundPerson);
System.out.println("Removed: " + foundPerson.getName());
} else {
System.out.println("Name not found!");
}
}

public static void cancelEvent() {
if(!currentOrganizer.hasEvent()) {
System.out.println("No event to cancel!");
return;
}

Event event = currentOrganizer.getOrganizedEvent();
System.out.print("Are you sure you want to cancel event: " + event.getEventName() + "? (yes/no): ");
String confirmation = input.nextLine();

if(confirmation.equalsIgnoreCase("yes")) {
currentOrganizer.cancelEvent();
System.out.println("Event cancelled successfully!");
} else {
System.out.println("Event cancellation cancelled.");
}
}   //========


}*/

