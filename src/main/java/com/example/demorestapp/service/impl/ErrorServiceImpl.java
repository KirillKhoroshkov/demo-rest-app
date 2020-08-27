package com.example.demorestapp.service.impl;

import com.example.demorestapp.exception.RecordNotFoundException;
import com.example.demorestapp.model.Error;
import com.example.demorestapp.repository.ErrorRepository;
import com.example.demorestapp.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ErrorServiceImpl implements ErrorService {

    private ErrorRepository errorRepository;

    @Autowired
    private void setErrorRepository(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @Override
    public Error saveError(Error error) {
        return errorRepository.save(error);
    }

    @Override
    public Error fetchLastCreatedError() {
        return errorRepository
                .findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "created")))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException("There are no error records"));
    }
}