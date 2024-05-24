package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryReview extends JpaRepository<Review,Long> {
}
