package model;

public abstract class Person {
    protected int id;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = "";
        this.address = "";
    }

    public Person(int id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }

    public abstract String getRole();
}
