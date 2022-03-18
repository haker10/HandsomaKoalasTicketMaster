package be;

public class User {
    int id;
    String typeOfUser;
    String login;
    String password;
    public User(int id, String typeOfUser, String login, String password){
        this.id=id;
        this.typeOfUser = typeOfUser;
        this.login = login;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public String getTypeOfUser(){
        return typeOfUser;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return "User{" + "id=" + id + ", type of user='" + typeOfUser + "', login='" + login + "', password='" + password + '\'' + '}';
    }
}
