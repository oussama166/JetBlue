package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPayment extends JpaRepository<Payment,Long> {
}
