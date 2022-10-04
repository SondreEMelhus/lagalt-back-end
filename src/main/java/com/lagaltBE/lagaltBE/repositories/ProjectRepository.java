package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
