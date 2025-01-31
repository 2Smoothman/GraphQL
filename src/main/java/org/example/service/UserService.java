package org.example.service;

import java.util.List;

import org.example.model.Bio;
import org.example.model.Profile;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (userRepository.count() == 0) {
            Bio bio1 = new Bio();
            bio1.setDescription("Разработчик Java");
            bio1.setInterests("Программирование, книги");

            Profile profile1 = new Profile();
            profile1.setFirstName("Иван");
            profile1.setLastName("Петров");
            profile1.setBio(bio1);
            bio1.setProfile(profile1);

            User user1 = new User();
            user1.setUsername("ivan");
            user1.setEmail("ivan@example.com");
            user1.setProfile(profile1);
            profile1.setUser(user1);

            userRepository.save(user1);
        }
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
            .map(user -> {
                user.setUsername(userDetails.getUsername());
                user.setEmail(userDetails.getEmail());
                if (userDetails.getProfile() != null) {
                    user.getProfile().setFirstName(userDetails.getProfile().getFirstName());
                    user.getProfile().setLastName(userDetails.getProfile().getLastName());
                    if (userDetails.getProfile().getBio() != null) {
                        user.getProfile().getBio().setDescription(userDetails.getProfile().getBio().getDescription());
                        user.getProfile().getBio().setInterests(userDetails.getProfile().getBio().getInterests());
                    }
                }
                return userRepository.save(user);
            })
            .orElse(null);
    }
} 