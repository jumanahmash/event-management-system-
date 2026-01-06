/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EventMangement;

// Exception 1
public class EventAlreadyExistsException extends Exception {
    public EventAlreadyExistsException(String message) {
        super(message);
    }
}

// Exception 2
class EmptyFieldException extends Exception {
    public EmptyFieldException(String message) {
        super(message);
    }
}

// Exception 3
class AttendeeNotFoundException extends Exception {
    public AttendeeNotFoundException(String message) {
        super(message);
    }
}