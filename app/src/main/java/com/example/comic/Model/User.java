package com.example.comic.Model;

public class User {
    public int id;
    public String email;
    public String pass;
    public String username;
    public String fullname;

    public User() {
    }

    public User(int id, String email, String pass, String username, String fullname) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.username = username;
        this.fullname = fullname;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}
