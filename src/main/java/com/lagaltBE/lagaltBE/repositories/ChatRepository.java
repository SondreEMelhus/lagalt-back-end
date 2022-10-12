package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
}
