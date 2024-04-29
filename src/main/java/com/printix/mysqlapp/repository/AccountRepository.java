package com.printix.mysqlapp.repository;

import com.printix.mysqlapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
