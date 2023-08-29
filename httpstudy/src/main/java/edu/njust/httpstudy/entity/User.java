package edu.njust.httpstudy.entity;


public class User {

    private int id;
    private String phonenumber , password;

    public User(){
    }

    public User(String phonenumber, String password) {
        this.phonenumber = phonenumber;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
