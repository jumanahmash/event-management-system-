package EventMangement;

import java.io.*;

public class FileHandler {
    private static final String DATA_FILE = "event_data.dat";
    
    //takes from program 
    
    public static void saveData(PLinkedList<Person> people, PLinkedList<Event> events) {
        try {
            FileOutputStream fileOut = new FileOutputStream(DATA_FILE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(people);
            out.writeObject(events);
            out.close();
            
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static Object[] loadData() {
        try {
            FileInputStream fileIn = new FileInputStream(DATA_FILE);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            PLinkedList<Person> people = (PLinkedList<Person>) in.readObject();
            PLinkedList<Event> events = (PLinkedList<Event>) in.readObject();
            in.close();
            // create and cast liked list 
            return new Object[]{people, events};
           // to return all type of objects we use object class in java 
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return null;
        }
    }
}