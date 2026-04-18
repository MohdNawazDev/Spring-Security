package org.web.nawaz.springsecurity03.entity;

import jakarta.persistence.*;

//import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 35)
    private String username;

    @Column(unique = true, length = 20)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(length = 100)
    private String fullName;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false, updatable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    public User() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public void addRole(Role role)
    {
        this.roles.add(role);
    }

    public static UserBuilder builder()
    {
        return new UserBuilder();
    }

    //making our own custom builder
    public static class UserBuilder {
        private String username;
        private String email;
        private String password;
        private String fullName;
        private boolean enabled = true;
        private boolean accountNonLocked = true;


        public UserBuilder username(String username)
        {
            this.username = username;
            return this;
        }

        public UserBuilder email(String email)
        {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password)
        {
            this.password = password;
            return this;
        }

        public UserBuilder fullName(String fullName)
        {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder enabled(boolean enabled)
        {
            this.enabled = enabled;
            return this;
        }
        public UserBuilder accountNonLocked(boolean accountNonLocked)
        {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public User build()
        {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setEnabled(enabled);
            user.setAccountNonLocked(accountNonLocked);

            return user;
        }

    }

}
