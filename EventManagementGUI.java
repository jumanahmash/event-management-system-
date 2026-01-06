package EventMangement;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class EventManagementGUI extends JFrame {
    
    private static final PLinkedList<Person> allPeople = new PLinkedList<>();
    private static Organizer currentOrganizer;
    
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    
    public EventManagementGUI() {
        setTitle("Event Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        mainPanel.add(createRegistrationPanel(), "REGISTRATION");
        mainPanel.add(createMainMenuPanel(), "MAIN_MENU");
        
        add(mainPanel);
        cardLayout.show(mainPanel, "REGISTRATION");
    }
    
    // شاشة التسجيل
    private JPanel createRegistrationPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        JLabel titleLabel = new JLabel("Organizer Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField mobileField = new JTextField();
        JTextField idField = new JTextField();
        JButton registerBtn = new JButton("Register");
        
        panel.add(titleLabel);
        panel.add(new JLabel(""));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Mobile:"));
        panel.add(mobileField);
        panel.add(new JLabel("Organizer ID:"));
        panel.add(idField);
        panel.add(new JLabel(""));
        panel.add(registerBtn);
        
        registerBtn.addActionListener(e -> {
            // ✅ 2) Unchecked Exception - نستدعي الميثود ونمسك الـ exception هنا
            try {
                validateRegistration(nameField.getText(), emailField.getText(), 
                                    mobileField.getText(), idField.getText());
                
                currentOrganizer = new Organizer(nameField.getText(), emailField.getText(), 
                                                mobileField.getText(), idField.getText());
                allPeople.insertAtFront(currentOrganizer);
                
                // إضافة بيانات تجريبية
                allPeople.insertAtFront(new Participant("Sara", "sara@email.com", "0552222222", "P001"));
                allPeople.insertAtFront(new Participant("Khalid", "khalid@email.com", "0553333333", "P002"));
                allPeople.insertAtFront(new VIPParticipant("Nora", "nora@email.com", "0554444444", "VIP001", "GOLD"));
                allPeople.insertAtFront(new VIPParticipant("Mohammed", "mohammed@email.com", "0555555555", "VIP002", "PLATINUM"));
                
                JOptionPane.showMessageDialog(this, "Welcome, " + nameField.getText() + "! (ID: " + idField.getText() + ")");
                cardLayout.show(mainPanel, "MAIN_MENU");
                
            } catch (IllegalArgumentException ex) {
                // Unchecked Exception - تم مسكه هنا (propagation)
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        return panel;
    }
    
    // ✅ 2) Unchecked Exception - الميثود يرمي exception ولا يمسكه (propagation)
    private void validateRegistration(String name, String email, String mobile, String id) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty!");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format!");
        }
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile cannot be empty!");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Organizer ID cannot be empty!");
        }
    }
    
    // القائمة الرئيسية
    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        
        JLabel titleLabel = new JLabel("Event Organizer System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        JButton btn1 = new JButton("1- Create New Event");
        JButton btn2 = new JButton("2- Add Attendee to Event");
        JButton btn3 = new JButton("3- Display Attendees by Type");
        JButton btn4 = new JButton("4- Remove Attendee from Event");
        JButton btn5 = new JButton("5- Cancel Event");
        JButton btn6 = new JButton("6- Exit");
        
        panel.add(titleLabel);
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        
        btn1.addActionListener(e -> createNewEvent());
        btn2.addActionListener(e -> addAttendee());
        btn3.addActionListener(e -> displayAttendeesByType());
        btn4.addActionListener(e -> removeAttendee());
        btn5.addActionListener(e -> cancelEvent());
        btn6.addActionListener(e -> System.exit(0));
        
        return panel;
    }
    
    // 1- إنشاء حدث جديد
    private void createNewEvent() {
        // ✅ 3) User-Defined Exception
        try {
            checkEventExists(); // رمي user-defined exception
            
            JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
            JTextField nameField = new JTextField();
            JTextField typeField = new JTextField();
            JTextField dateField = new JTextField();
            JTextField locationField = new JTextField();
            
            panel.add(new JLabel("Event Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Event Type:"));
            panel.add(typeField);
            panel.add(new JLabel("Event Date:"));
            panel.add(dateField);
            panel.add(new JLabel("Event Location:"));
            panel.add(locationField);
            
            int result = JOptionPane.showConfirmDialog(this, panel, "Create New Event", 
                JOptionPane.OK_CANCEL_OPTION);
            
            if (result == JOptionPane.OK_OPTION) {
                // ✅ 3) User-Defined Exception للحقول الفارغة
                validateEventFields(nameField.getText(), typeField.getText(), 
                                  dateField.getText(), locationField.getText());
                
                String eventId = "EVT-" + currentOrganizer.getOrganizerId();
                Event event = currentOrganizer.createEvent(eventId, nameField.getText(), 
                                                          typeField.getText(), dateField.getText(), 
                                                          locationField.getText());
                
                if (event != null) {
                    JOptionPane.showMessageDialog(this, "Event created successfully: " + 
                        nameField.getText() + "\n\n" + event.toString());
                    
                    // ✅ 1) Checked Exception - حفظ البيانات في ملف
                    saveEventToFile(event);
                }
            }
        } catch (EventAlreadyExistsException ex) {
            // ✅ 3) User-Defined Exception - تم مسكه
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Event Exists", JOptionPane.WARNING_MESSAGE);
        } catch (EmptyFieldException ex) {
            // ✅ 3) User-Defined Exception - تم مسكه
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Empty Field", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ✅ 3) User-Defined Exception - التحقق من وجود حدث
    private void checkEventExists() throws EventAlreadyExistsException {
        if (currentOrganizer.hasEvent()) {
            throw new EventAlreadyExistsException("You already have an active event!\nCurrent event: " + 
                currentOrganizer.getOrganizedEvent().getEventName());
        }
    }
    
    // ✅ 3) User-Defined Exception - التحقق من الحقول الفارغة
    private void validateEventFields(String name, String type, String date, String location) throws EmptyFieldException {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyFieldException("Event name cannot be empty!");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new EmptyFieldException("Event type cannot be empty!");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new EmptyFieldException("Event date cannot be empty!");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new EmptyFieldException("Event location cannot be empty!");
        }
    }
    
    // ✅ 1) Checked Exception - حفظ الحدث في ملف (try-catch داخل نفس الميثود)
    private void saveEventToFile(Event event) {
        try {
            File file = new File("event_data.txt");
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(event.toString() + "\n");
            }
            JOptionPane.showMessageDialog(this, "Event saved to file successfully!");
        } catch (IOException ex) {
            // ✅ 1) Checked Exception - تم مسكه داخل نفس الميثود
            JOptionPane.showMessageDialog(this, "Failed to save event to file: " + ex.getMessage(), 
                "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // 2- إضافة حاضر
    private void addAttendee() {
        if (!currentOrganizer.hasEvent()) {
            JOptionPane.showMessageDialog(this, "No active event found! Please create an event first.");
            return;
        }
        
        Event event = currentOrganizer.getOrganizedEvent();
        
        StringBuilder list = new StringBuilder("Available People:\n\n");
        Node<Person> current = allPeople.getHead();
        boolean foundAny = false;
        
        while (current != null) {
            Person p = current.getData();
            if (p instanceof Participant && !p.equals(currentOrganizer)) {
                list.append(" - ").append(p.getName()).append(" (").append(p.getType()).append(") - ")
                    .append(p.getEmail()).append("\n");
                foundAny = true;
            }
            current = current.getNext();
        }
        
        if (!foundAny) {
            JOptionPane.showMessageDialog(this, "No available participants");
            return;
        }
        
        String personName = JOptionPane.showInputDialog(this, list.toString() + "\nEnter person name to add:");
        
        if (personName != null && !personName.isEmpty()) {
            // ✅ 3) User-Defined Exception
            try {
                Person foundPerson = findParticipant(personName);
                event.addAttendee(foundPerson);
                JOptionPane.showMessageDialog(this, "Successfully added: " + foundPerson.getName() + " to the event");
            } catch (AttendeeNotFoundException ex) {
                // ✅ 3) User-Defined Exception - تم مسكه
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // ✅ 3) User-Defined Exception - البحث عن حاضر
    private Person findParticipant(String name) throws AttendeeNotFoundException {
        Node<Person> current = allPeople.getHead();
        
        while (current != null) {
            Person p = current.getData();
            if (p.getName().equals(name) && p instanceof Participant) {
                return p;
            }
            current = current.getNext();
        }
        
        throw new AttendeeNotFoundException("Attendee '" + name + "' not found! Please check spelling.");
    }
    
    // 3- عرض الحضور
    private void displayAttendeesByType() {
        if (!currentOrganizer.hasEvent()) {
            JOptionPane.showMessageDialog(this, "No active event found! Please create an event first.");
            return;
        }
        
        Event event = currentOrganizer.getOrganizedEvent();
        PLinkedList<Person> attendees = event.getAttendees();
        
        String[] options = {"VIP (V)", "Regular (R)", "All (A)"};
        int choice = JOptionPane.showOptionDialog(this, "Show (V: VIP, R: Regular, A: All):", 
            "Display Attendees", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
            null, options, options[2]);
        
        if (choice == -1) return;
        
        StringBuilder result = new StringBuilder();
        Node<Person> current = attendees.getHead();
        boolean found = false;
        
        switch (choice) {
            case 0 -> {
                // VIP
                result.append("VIP Attendees:\n\n");
                while (current != null) {
                    Person p = current.getData();
                    if (p instanceof VIPParticipant vip) {
                        result.append("- ").append(vip.getName()).append(" (").append(vip.getVipLevel())
                                .append(" VIP) - ").append(vip.getEmail()).append("\n");
                        found = true;
                    }
                    current = current.getNext();
                }   if (!found) result.append("No VIP attendees found.");
            }
            case 1 -> {
                // Regular
                result.append("Regular Attendees:\n\n");
                while (current != null) {
                    Person p = current.getData();
                    if (p instanceof Participant && !(p instanceof VIPParticipant)) {
                        result.append("- ").append(p.getName()).append(" - ").append(p.getEmail()).append("\n");
                        found = true;
                    }
                    current = current.getNext();
                }   if (!found) result.append("No regular attendees found.");
            }
            case 2 -> {
                // All
                result.append("All Attendees:\n\n");
                while (current != null) {
                    Person p = current.getData();
                    result.append("- ").append(p.getName()).append(" - ").append(p.getType())
                            .append(" - ").append(p.getEmail()).append("\n");
                    found = true;
                    current = current.getNext();
                }   if (!found) result.append("No attendees found.");
            }
            default -> {
            }
        }
        
        JOptionPane.showMessageDialog(this, result.toString());
    }
    
    // 4- حذف حاضر
    private void removeAttendee() {
        if (!currentOrganizer.hasEvent()) {
            JOptionPane.showMessageDialog(this, "No active event!");
            return;
        }
        
        Event event = currentOrganizer.getOrganizedEvent();
        PLinkedList<Person> attendees = event.getAttendees();
        
        if (attendees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "List is empty.");
            return;
        }
        
        StringBuilder list = new StringBuilder("Current Attendees:\n\n");
        Node<Person> current = attendees.getHead();
        
        while (current != null) {
            list.append(current.getData()).append("\n");
            current = current.getNext();
        }
        
        String personName = JOptionPane.showInputDialog(this, list.toString() + "\nEnter name to remove:");
        
        if (personName != null && !personName.isEmpty()) {
            Person foundPerson = null;
            current = attendees.getHead();
            
            while (current != null) {
                if (current.getData().getName().equals(personName)) {
                    foundPerson = current.getData();
                    break;
                }
                current = current.getNext();
            }
            
            if (foundPerson != null) {
                event.removeAttendee(foundPerson);
                JOptionPane.showMessageDialog(this, "Removed: " + foundPerson.getName());
            } else {
                JOptionPane.showMessageDialog(this, "Name not found!");
            }
        }
    }
    
    // 5- إلغاء الحدث
    private void cancelEvent() {
        if (!currentOrganizer.hasEvent()) {
            JOptionPane.showMessageDialog(this, "No event to cancel!");
            return;
        }
        
        Event event = currentOrganizer.getOrganizedEvent();
        int result = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to cancel event: " + event.getEventName() + "? (yes/no):",
            "Cancel Event Confirmation", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            currentOrganizer.cancelEvent();
            JOptionPane.showMessageDialog(this, "Event cancelled successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Event cancellation cancelled.");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EventManagementGUI gui = new EventManagementGUI();
            gui.setVisible(true);
        });
    }
}