package EventMangement;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String email;
    private String mobile;

    public Person(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public abstract String getType();

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Mobile: " + mobile;
    }
    
   
}