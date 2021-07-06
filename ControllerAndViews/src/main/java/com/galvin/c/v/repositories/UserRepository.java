package com.galvin.c.v.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.galvin.c.v.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
}
