package com.skrylov.WebApp.repos;

import com.skrylov.WebApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsernameIgnoreCase(String username);
}
