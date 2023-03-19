package com.jwt.jwtAuth.model;

import javax.persistence.*;

@Entity
@Table(name = "jwt_user")
public class JwtRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String username;
    String password;
    String role;

    public JwtRequest() {
    }

    enum role{ADMIN,USER}
//    Set<Role> role = new HashSet<>();

//    @OneToMany
//    private List<Role> role;


    public JwtRequest(String username, String password, String  role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    @Override
    public String toString() {
        return "JwtRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
