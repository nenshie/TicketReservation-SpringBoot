package com.nevena.repository;

import com.nevena.entities.CinemaAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaAccountRepository extends JpaRepository<CinemaAccount, Long> {

    Optional<CinemaAccount> findFirstByOrderByIdAsc();

}
