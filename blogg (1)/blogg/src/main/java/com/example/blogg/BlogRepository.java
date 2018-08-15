package com.example.blogg;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Posts, Integer> {
    Posts findByUserID(int userID);
    List findAll();
}
