package com.hive.capstone.causes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CauseService {
    @Autowired
    CauseRepository repo;

    public Cause getById(String id) {
        return repo.findById(id);
    }
}
