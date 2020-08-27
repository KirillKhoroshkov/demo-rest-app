package com.example.demorestapp.service.impl;

import com.example.demorestapp.exception.RecordNotFoundException;
import com.example.demorestapp.model.Profile;
import com.example.demorestapp.repository.ProfileRepository;
import com.example.demorestapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    private void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile fetchLastCreatedProfile() {
        return profileRepository
                .findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "created")))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("There are no profile records"));
    }

    @Override
    public List<Profile> fetchProfiles(int page, int size) {
        return profileRepository.findAll(PageRequest.of(page, size, Sort.by("id"))).getContent();
    }

    @Override
    public Profile fetchProfile(int id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Profile with id " + id + " not found"));
    }

    @Override
    public Profile fetchProfileByEmail(String email) {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("Profile with email " + email + " not found"));
    }
}