package com.example.demorestapp.controller;

import com.example.demorestapp.form.EmailForm;
import org.springframework.http.MediaType;
import com.example.demorestapp.model.Profile;
import com.example.demorestapp.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
    public final Map<String, Integer> createProfile(@Valid @RequestBody Profile profile) {
        Profile createdProfile = profileService.saveProfile(profile);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("idUser", createdProfile.getId());
        return resultMap;
    }

    @GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Profile fetchLastCreatedProfile() {
        return profileService.fetchLastCreatedProfile();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Profile> fetchProfiles(@RequestParam("page") int page, @RequestParam("size") int size) {
        return profileService.fetchProfiles(page + 1, size);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Profile fetchProfile(@PathVariable("id") int id) {
        return profileService.fetchProfile(id);
    }

    @PostMapping(
            value = "/get",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public final Profile fetchProfileByEmail(@RequestBody EmailForm emailForm) {
        String email = emailForm.getEmail();
        return profileService.fetchProfileByEmail(email);
    }
}