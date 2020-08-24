package com.example.demorestapp.repository;

import com.example.demorestapp.model.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Integer> {

    @Query("select p from Profile p where lower(p.email) = lower(:email)")
    Optional<Profile> findByEmail(String email);

    @Query("select p from Profile p where lower(p.name) = lower(:name)")
    Optional<Profile> findByName(String name);
}