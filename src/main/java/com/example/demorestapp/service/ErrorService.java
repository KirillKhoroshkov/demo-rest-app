package com.example.demorestapp.service;

import com.example.demorestapp.model.Error;

public interface ErrorService {

    Error saveError(Error error);

    Error fetchLastCreatedError();
}