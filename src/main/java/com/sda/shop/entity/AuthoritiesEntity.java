package com.sda.shop.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class AuthoritiesEntity {
    @Id
    @Column(length = 50)
    private String username;
    private String authority;
    @ManyToOne
    @JoinColumn(name = "username", insertable = false,updatable = false)
    private UserEntity user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}




