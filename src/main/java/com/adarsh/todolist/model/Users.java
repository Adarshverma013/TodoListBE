package com.adarsh.todolist.model;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity(name = "USERS")
public class Users {



    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int id;


    @Column(name = "USER_NAME",unique = true,nullable = false)
    private String userName;

    @Column(name = "PASSWORD",nullable = false)
    private String password;



    @Column(name = "EMAIL_ID",unique = true,nullable = false)
    private String emailId;

    public Users(int id, String userName, String emailId,String password) {
        this.id = id;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
    }
    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
