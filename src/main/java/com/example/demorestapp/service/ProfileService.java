package com.example.demorestapp.service;

import com.example.demorestapp.model.Profile;
import java.util.List;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    Profile fetchLastCreatedProfile();

    List<Profile> fetchProfiles(int page, int size);

    Profile fetchProfile(int id);

    Profile fetchProfileByEmail(String email);
}