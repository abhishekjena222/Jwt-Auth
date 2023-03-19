package com.jwt.jwtAuth.repo;

import com.jwt.jwtAuth.model.JwtRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<JwtRequest, Long> {
    JwtRequest findByUsernameLike(String username);
}
