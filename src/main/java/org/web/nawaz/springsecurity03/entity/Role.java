package org.web.nawaz.springsecurity03.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(length = 200)
    private String description;

    public Role() {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Role(String username, String description)
    {
        this.name = name;
        this.description = description;
    }

    //before making them builder
    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public static class RoleBuilder
    {
        private String name;
        private String description;

        //these are methods
        public RoleBuilder name(String name)
        {
            this.name = name;
            return this;
        }

        public RoleBuilder description(String description)
        {
            this.description = description;
            return this;
        }

        public Role build()
        {
            return new Role(name, description);
        }
    }

}
