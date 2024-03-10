package com.example.testeredis.repositories;

import com.example.testeredis.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
    User findFirstByEmail(String email);
}
