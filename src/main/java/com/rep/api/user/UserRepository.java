package com.rep.api.user;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByTag(String tag);

    @Query(value = "SELECT u FROM User u ORDER BY RAND()")
    Set<User> findRandomUser(PageRequest pageable);
}