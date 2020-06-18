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
    public Customer(String username, String name, String email, String phone, String address) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
