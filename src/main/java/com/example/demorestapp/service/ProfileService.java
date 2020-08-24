package com.example.demorestapp.service;

import com.example.demorestapp.model.Profile;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface ProfileService {

    Profile createProfile(Profile profile);

    Optional<Profile> fetchLastCreatedProfile();

    Page<Profile> fetchProfiles(int page, int size);

    Optional<Profile> fetchProfile(int id);

    Optional<Profile> fetchProfileByEmail(String email);
}