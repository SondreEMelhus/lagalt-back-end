package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
