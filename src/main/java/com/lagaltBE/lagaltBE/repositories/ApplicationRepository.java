package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
}
