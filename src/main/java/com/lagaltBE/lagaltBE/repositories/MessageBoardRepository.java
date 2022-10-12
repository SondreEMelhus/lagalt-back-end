package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.MessageBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageBoardRepository extends JpaRepository<MessageBoard,Integer> {
}
