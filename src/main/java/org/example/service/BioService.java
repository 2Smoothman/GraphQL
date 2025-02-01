package org.example.service;

import org.example.dto.UpdateBioRequest;
import org.example.model.Bio;
import org.example.repository.BioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BioService {
    private final BioRepository bioRepository;

    @Transactional(readOnly = true)
    public Bio getBioById(Long id) {
        return bioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Bio updateBio(Long id, UpdateBioRequest request) {
        return bioRepository.findById(id)
            .map(bio -> {
                bio.setDescription(request.getDescription());
                bio.setInterests(request.getInterests());
                return bioRepository.save(bio);
            })
            .orElse(null);
    }
} 