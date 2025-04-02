package com.escapeRoom.repository;

import com.escapeRoom.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
