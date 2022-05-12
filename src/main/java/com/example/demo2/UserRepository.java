package com.example.demo2;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{_id: ?0, password: ?1}")
    User getUserByemailAndpassword(String email,String password);
    User findByPassword(String password);
}
