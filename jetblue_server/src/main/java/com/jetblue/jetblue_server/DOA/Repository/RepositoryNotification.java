package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.Notification;
import com.jetblue.jetblue_server.DOA.Modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryNotification extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT n FROM Notification n WHERE n.Message LIKE %?1%")
    Optional<List<Notification>> findByMessage(String message);

    @Query(value = "SELECT n FROM Notification n WHERE n.Message LIKE %?1% AND n.userId.UserId = ?2")
    Optional<List<Notification>> findByMessageAndUserId(String message, long userId);
    @Query(
            value="SELECT e FROM Notification e WHERE e.userId.UserId = ?1"
    )
    Optional<List<Notification>> findByUserId(long user_id);
}



