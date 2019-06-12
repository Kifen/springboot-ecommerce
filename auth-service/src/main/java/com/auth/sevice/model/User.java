package com.auth.sevice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String username, String email, @Size(max = 60) String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Transient
    public void addRole(Role role){
        String x = role.getRoleName().name();
        switch (x){
            case "USER":
                this.roles.add(new Role(RoleName.USER));
                break;
            case "ADMIN":
                this.roles.add(new Role(RoleName.ADMIN));
                break;
            default:
                this.roles.add(new Role(RoleName.USER));
                break;
        }
    }
}
