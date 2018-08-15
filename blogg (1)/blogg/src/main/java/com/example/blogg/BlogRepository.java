package com.example.blogg;

import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Posts, Integer> {
    Posts findByUserID(int userID);
}
