package proiect.fis.store.model;

public class Customer {
    private String username ;
    private String name ;
    private String password ;
    private String email ;
    private String phone;
    private String address ;
    private int password_changed ;

    public Customer(String username,String name,String password,int password_changed){
        this.username = username;
        this.name = name ;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getPassword_changed() {
        return password_changed;
    }
}
