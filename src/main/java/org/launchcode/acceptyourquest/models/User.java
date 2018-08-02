package org.launchcode.acceptyourquest.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Column(unique=true)
    @Size(min=3, max=15)
    private String userName;

    @NotNull
    @Size(min=8)
    private String password;

    private String authorName;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.authorName = "";
    }

    public User(String userName, String password, String authorName) {
        this.userName = userName;
        this.password = password;
        this.authorName = authorName;
    }


    //public comparePassword(String userName, String password) { }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
