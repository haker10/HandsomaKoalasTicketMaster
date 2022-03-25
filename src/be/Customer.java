package be;

public class Customer {
    String name;
    String email;
    String phone;

    public Customer(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
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
        return "Name: " + name + "  Email: " + email + "  Phone: " + phone + "\n";
    }
}
