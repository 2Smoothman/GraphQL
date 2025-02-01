package org.example.service;

import org.example.dto.UpdateProfileRequest;
import org.example.model.Profile;
import org.example.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Transactional
    public Profile updateProfile(Long id, UpdateProfileRequest request) {
        return profileRepository.findById(id)
            .map(profile -> {
                profile.setFirstName(request.getFirstName());
                profile.setLastName(request.getLastName());
                profile.setAboutMe(request.getAboutMe());
                profile.setPictureUrl(request.getPictureUrl());
                return profileRepository.save(profile);
            })
            .orElse(null);
    }
} 