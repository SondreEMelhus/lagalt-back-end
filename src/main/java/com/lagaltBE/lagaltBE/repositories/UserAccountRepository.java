package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
}
