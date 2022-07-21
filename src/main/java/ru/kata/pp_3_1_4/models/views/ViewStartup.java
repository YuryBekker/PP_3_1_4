package ru.kata.pp_3_1_4.models.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.kata.pp_3_1_4.models.Role;

import java.util.List;

public class ViewStartup {
    @JsonProperty("id")
    public long id;
    @JsonProperty("username")
    public String username;
    @JsonProperty("email")
    public String email;
    @JsonProperty("rolesToString")
    public String rolesToString;
    @JsonProperty("isAdmin")
    public boolean isAdmin;
    @JsonProperty("rolesToList")
    public List<Role> roles;

    public ViewStartup(long id, String username, String email, String rolesToString, boolean isAdmin, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isAdmin = isAdmin;
        this.roles = roles;
        this.rolesToString = rolesToString;
    }
}
