package com.example.demorestapp.controller;

import com.example.demorestapp.form.EmailForm;
import org.springframework.http.MediaType;
import com.example.demorestapp.model.Profile;
import com.example.demorestapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private ProfileService profileService;

    @Autowired
    private void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping(
            value = "/set",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Map<String, Integer> createProfile(@Valid @RequestBody Profile profile) {
        Profile createdProfile = profileService.createProfile(profile);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("idUser", createdProfile.getId());
        return resultMap;
    }

    @GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
    Profile fetchLastCreatedProfile() {
        return profileService.fetchLastCreatedProfile()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profiles are missing"
                ));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Iterable<Profile> fetchProfiles(@RequestParam("page") int page, @RequestParam("size") int size) {
        return profileService.fetchProfiles(page, size).getContent();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Profile fetchProfile(@PathVariable("id") int id) {
        return profileService.fetchProfile(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profile with id " + id + " not found"
                ));
    }

    @PostMapping(
            value = "/get",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    Profile fetchProfileByEmail(@Valid @RequestBody EmailForm emailForm) {
        String email = emailForm.getEmail();
        return profileService.fetchProfileByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profile with email " + email + " not found"
                ));
    }
}