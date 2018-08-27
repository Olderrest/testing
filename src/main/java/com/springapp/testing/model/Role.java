package com.springapp.testing.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends Model {

    @Column(name = "title", length = 20)
    private String title;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "title='" + title + '\'' +
                ", users=" + users +
                '}';
    }
}
