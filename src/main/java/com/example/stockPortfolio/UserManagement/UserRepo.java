package com.example.stockPortfolio.UserManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

//we are using jpa to get some pre-defined methods, so we can access database through those
@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    // existsByEmail return boolean
    boolean existsByEmail(String email);

    //findByEmail returns object, we can also put (UserModel userModel)
    //but we wont have freedom to use inbuilt methods, it causes nullpointerexception
    Optional<UserModel> findByEmail(String email);
}
