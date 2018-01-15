package com.bogdevich.profile.repository;

import com.bogdevich.profile.entity.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository("profileRepository")
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByEmail(String email);

    @Query(
            value = "" +
                    "SELECT `user`.id, `user`.name, `user`.lastname, `user`.email, `user`.skype, `user`.phone, `user`.room " +
                    "FROM phonebook.`user` " +
                    "JOIN phonebook.`user_projects` " +
                    "ON `user`.`id` = `user_projects`.user_id " +
                    "JOIN phonebook.`project` " +
                    "ON `user_projects`.project_id = `project`.id " +
                    "WHERE `project`.id = ?1",
            countQuery = "" +
                    "SELECT COUNT(*) " +
                    "FROM phonebook.`user` " +
                    "JOIN phonebook.`user_projects` " +
                    "ON `user`.`id` = `user_projects`.user_id " +
                    "JOIN phonebook.`project` " +
                    "ON `user_projects`.project_id = `project`.id " +
                    "WHERE `project`.id = ?1",
            nativeQuery = true)
    List<Profile> findProfilesByProjectId(Long projectID, Pageable pageable);
}
