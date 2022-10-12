package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Account;
import com.lagaltBE.lagaltBE.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    @Query("select a from Account a where a.username = ?1")
    Account findByName(String name);
}
