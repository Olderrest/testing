package com.springapp.testing.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;


@Entity
@Table(name = "users")
public class User extends Model {

    @Size(min = 3, max = 16, message = "Length of login must be between 3 and 20 characters")
    @Column(name = "login", unique = true, length = 20, nullable = false)
    private String login;

    @Size(min = 6, max = 16, message = "Length of password must be between 6 and 20 characters")
    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Size(min = 3, max = 16, message = "Length of first name must be between 3 and 20 characters")
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Size(min = 3, max = 16, message = "Length of first name must be between 3 and 20 characters")
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role_id", nullable=false)
    private Role role;

    @Column(name = "block", nullable = false)
    private boolean block = false;

    @OneToMany(mappedBy = "user")
    private Set<Result> results;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, Role role, Boolean block) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.block = block;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role.getTitle() +
                ", block=" + block +
                '}';
    }
}
