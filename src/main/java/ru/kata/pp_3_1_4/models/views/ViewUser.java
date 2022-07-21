package ru.kata.pp_3_1_4.models.views;

public class ViewUser {
    public long id;
    public String username;
    public String email;
    public String roles;

    public ViewUser(long id, String username, String email, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

}