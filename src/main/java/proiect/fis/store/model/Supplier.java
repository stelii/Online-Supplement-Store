package proiect.fis.store.model;

public class Supplier {
    private String username ;
    private String name ;
    private String password ;
    private int password_changed ;

    public Supplier(String username, String name, String password, int password_changed) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.password_changed = password_changed;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPassword_changed() {
        return password_changed;
    }
}
