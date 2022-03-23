package be;

public class Customer {
    int id;
    String name;
    String email;
    String phone;

    public Customer(int id, String name, String email, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPhone(){
        return phone;
    }

    public String toString(){
        return id + name + email + phone;
    }
}
