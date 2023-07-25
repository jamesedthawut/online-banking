package com.project.repository;

import com.project.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, String> {


    Optional<Account> findById(String id);

    List<Account> findByAccountNumber(String accountNumber);

    Optional<Account> findByDate(LocalDateTime date);

}
