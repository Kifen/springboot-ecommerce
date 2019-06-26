package com.auth.sevice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;
    private String email;

    //@JsonIgnore
    @Size(max = 100)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Role> roles = new ArrayList<>();

    public User(String name, String username, String email, @Size(max = 60) String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Transient
    public void addRole(Role role){
        roles.add(role);
    }
}
