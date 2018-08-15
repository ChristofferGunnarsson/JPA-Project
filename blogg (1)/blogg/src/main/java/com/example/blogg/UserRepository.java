package com.example.blogg;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {

    Users findByUsernameAndPassword(String username, String password);
}