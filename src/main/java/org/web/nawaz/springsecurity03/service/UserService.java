package org.web.nawaz.springsecurity03.service;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.web.nawaz.springsecurity03.dto.RegisterRequest;
import org.web.nawaz.springsecurity03.entity.Role;
import org.web.nawaz.springsecurity03.entity.User;
import org.web.nawaz.springsecurity03.repository.RoleRepository;
import org.web.nawaz.springsecurity03.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User RegisterUser(RegisterRequest request)
    {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new Error("Username already exists " + request.getUsername());
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new Error("Email already exists " + request.getEmail());
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    return roleRepository.save(Role.builder()
                            .name("USER")
                            .description("Standard test user")
                            .build()
                    );
                });


        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) //we are storing password in db in encoded form
                .fullName(request.getFullName())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        user.addRole(userRole);

       User savedUser = userRepository.save(user);
        return savedUser;

    }

    @Transactional
    public User RegisterAdmin(RegisterRequest request)
    {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new Error("Username already exists " + request.getUsername());
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new Error("Email already exists " + request.getEmail());
        }

      Role userRole =  roleRepository.findByName("USER")
                .orElseGet(() -> {
                    return roleRepository.save(Role.builder()
                                    .name("ROLE_USER")
                                    .description("Standard User")
                            .build());
                });

      Role adminRole = roleRepository.findByName("ADMIN")
              .orElseGet(() -> {
                  return roleRepository.save(Role.builder()
                                  .name("ROLE_ADMIN")
                                  .description("Admin User")
                          .build());
              });

        User admin = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .enabled(true)
                .accountNonLocked(true)
                .build();

        admin.addRole(userRole);
        admin.addRole(adminRole);

      User adminUser =  userRepository.save(admin);
      return adminUser;
    }

    public User findByUserName(String username)
    {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }


}
