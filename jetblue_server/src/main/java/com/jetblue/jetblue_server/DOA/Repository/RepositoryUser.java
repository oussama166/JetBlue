package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User, Long> {

    @Query("select (count(u) > 0) from User u where u.Email = ?1")
    boolean Email(String Email);

    @Query(
            "SELECT us FROM User us WHERE us.Email LIKE ?1 AND us.Password LIKE ?2"
    )
    Optional<User> findByEmailAndPassword(String Email, String Password);

    @Query(
            value = "SELECT us from User us WHERE us.Email = ?1"
    )
    Optional<User> findByEmail(String Email);
}
