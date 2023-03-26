package com.usermicroservice.usermicroservice.repository;

import com.usermicroservice.usermicroservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
