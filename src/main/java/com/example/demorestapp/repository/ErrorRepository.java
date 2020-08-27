package com.example.demorestapp.repository;

import com.example.demorestapp.model.Error;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ErrorRepository extends PagingAndSortingRepository<Error, Integer> { }