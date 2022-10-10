package com.lagaltBE.lagaltBE.repositories;

import com.lagaltBE.lagaltBE.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query("select p from Project p where p.title like %?1%")
    Set<Project> findAllByName(String name);
}
