package org.example.controller;

import java.util.List;

import org.example.dto.CreateUserInput;
import org.example.model.Bio;
import org.example.model.Profile;
import org.example.model.User;
import org.example.service.BioService;
import org.example.service.ProfileService;
import org.example.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final BioService bioService;
    private final SubscriptionController subscriptionController;

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    public User createUser(@Argument("input") CreateUserInput input) {
        return userService.createUserWithProfile(input);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument("input") User userInput) {
        return userService.updateUser(id, userInput);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        userService.deleteUser(id);
        return true;
    }

    @QueryMapping
    public User me(Authentication authentication) {
        String username = authentication.getName();
        return userService.getUserByUsername(username);
    }

    @QueryMapping
    public Profile myProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        if (user.getProfile() == null) {
            throw new RuntimeException("Profile not found");
        }
        return user.getProfile();
    }

    @QueryMapping
    public Bio myBio(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        if (user.getProfile() == null || user.getProfile().getBio() == null) {
            throw new RuntimeException("Bio not found");
        }
        return user.getProfile().getBio();
    }

    @SchemaMapping(typeName = "User", field = "recommendations")
    public List<User> getRecommendations(User user) {
        return userService.getUsersByIds(user.getRecommendations());
    }

    @SchemaMapping(typeName = "User", field = "connections")
    public List<User> getConnections(User user) {
        return userService.getUsersByIds(user.getConnections());
    }

    @QueryMapping
    public Bio bioById(@Argument Long id) {
        return bioService.getBioById(id);
    }

    @QueryMapping
    public Profile profileById(@Argument Long id) {
        return profileService.getProfileById(id);
    }

    @MutationMapping
    public Boolean sendMessage(@Argument String message) {
        subscriptionController.sendMessage(message);
        return true;
    }

    @SchemaMapping(typeName = "User")
    public Profile profile(User user) {
        return user.getProfile();
    }
    
    @SchemaMapping(typeName = "User")
    public Bio bio(User user) {
        return user.getBio();
    }
    
    @SchemaMapping(typeName = "Profile")
    public User user(Profile profile) {
        return profile.getUser();
    }
    
    @SchemaMapping(typeName = "Bio")
    public Profile profile(Bio bio) {
        return bio.getProfile();
    }
} 