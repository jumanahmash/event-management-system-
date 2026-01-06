package EventMangement;

import java.io.Serializable;

public class Participant extends Person {
   
    private String participantId;

    public Participant(String name, String email, String mobile, String participantId) {
        super(name, email, mobile);
        this.participantId = participantId;
    }

    public String getType() {
        return "Regular Participant";
    }

    public String getParticipantId() { return participantId; }
    public void setParticipantId(String participantId) { this.participantId = participantId; }

    public String toString() {
        return super.toString() + ", ID: " + participantId;
    }
    

    
}