package com.example.demorestapp.service.impl;

import com.example.demorestapp.model.Profile;
import com.example.demorestapp.repository.ProfileRepository;
import com.example.demorestapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;

    @Autowired
    private void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Optional<Profile> fetchLastCreatedProfile() {
        return profileRepository
                .findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "created")))
                .stream()
                .findFirst();
    }

    @Override
    public Page<Profile> fetchProfiles(int page, int size) {
        return profileRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Profile> fetchProfile(int id) {
        return profileRepository.findById(id);
    }

    @Override
    public Optional<Profile> fetchProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }
}