package EventMangement;

import java.io.Serializable;

public class VIPParticipant extends Participant  {
  
    
    private String vipLevel;

    public VIPParticipant(String name, String email, String mobile, 
                         String participantId, String vipLevel) {
        super(name, email, mobile, participantId);
        this.vipLevel = vipLevel;
    }

    public String getType() {
        return "VIP Participant (" + vipLevel + ")";
    }

    public String getVipLevel() { return vipLevel; }
    public void setVipLevel(String vipLevel) { this.vipLevel = vipLevel; }

    public String toString() {
        return super.toString() + ", VIP Level: " + vipLevel;
    }
    
    
   
}