package com.bogdevich.profile.repository;

import com.bogdevich.profile.entity.domain.Profile;
import com.bogdevich.profile.entity.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
