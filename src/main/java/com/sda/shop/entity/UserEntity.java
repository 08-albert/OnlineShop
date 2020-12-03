package com.sda.shop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @Column(length = 50)
    private String username;
    @Column(length = 150)
    private String password;
    private Boolean enabled;
    @OneToMany(mappedBy = "user")
    private List<AuthoritiesEntity>authorities;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthoritiesEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthoritiesEntity> authorities) {
        this.authorities = authorities;
    }
}
