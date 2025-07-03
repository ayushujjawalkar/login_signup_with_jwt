package com.ayush.login_signup_with_jwt.repository;
import org.springframework.data.repository.CrudRepository;
import com.ayush.login_signup_with_jwt.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationCode(String verificationCode);
}
