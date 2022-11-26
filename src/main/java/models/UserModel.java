package models;

import org.mindrot.jbcrypt.BCrypt;

public class UserModel {
    private int id;
    private String email;
    private String name;
    private String password;
    public UserModel(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public  UserModel(){

    }
    public int getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
}
