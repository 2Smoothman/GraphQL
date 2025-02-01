package org.example.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.example.dto.CreateUserInput;
import org.example.model.Bio;
import org.example.model.Profile;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
            user1.setPassword(passwordEncoder.encode("password123"));
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
                    user.getProfile().setAboutMe(userDetails.getProfile().getAboutMe());
                    user.getProfile().setPictureUrl(userDetails.getProfile().getPictureUrl());
                    if (userDetails.getProfile().getBio() != null) {
                        user.getProfile().getBio().setDescription(userDetails.getProfile().getBio().getDescription());
                        user.getProfile().getBio().setInterests(userDetails.getProfile().getBio().getInterests());
                    }
                }
                return userRepository.save(user);
            })
            .orElse(null);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<User> getUsersByIds(Collection<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Transactional
    public User createUserWithProfile(CreateUserInput input) {
        Bio bio = new Bio();
        bio.setDescription(input.getProfile().getBio().getDescription());
        bio.setInterests(input.getProfile().getBio().getInterests());

        Profile profile = new Profile();
        profile.setFirstName(input.getProfile().getFirstName());
        profile.setLastName(input.getProfile().getLastName());
        profile.setAboutMe(input.getProfile().getAboutMe());
        profile.setPictureUrl(input.getProfile().getPictureUrl());
        profile.setBio(bio);
        bio.setProfile(profile);

        User user = new User();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setProfile(profile);
        profile.setUser(user);

        if (input.getRecommendations() != null) {
            user.setRecommendations(input.getRecommendations().stream()
                .map(Long::valueOf)
                .collect(Collectors.toSet()));
        }
        
        if (input.getConnections() != null) {
            user.setConnections(input.getConnections().stream()
                .map(Long::valueOf)
                .collect(Collectors.toSet()));
        }

        return userRepository.save(user);
    }
} 
