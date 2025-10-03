package com.nevena.repository;

import com.nevena.entities.CinemaAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CinemaAccountRepository extends JpaRepository<CinemaAccount, Long> {

    Optional<CinemaAccount> findFirstByOrderByIdAsc();

}
