package com.bogdevich.profile.repository;

import com.bogdevich.profile.entity.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("profileRepository")
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByEmail(String email);

    /**
     * String "\n#pageable\n" is required for pagination query.
     *
     * @param projectID - identification project number.
     * @param pageable - object with values of page number and page limit
     * @return {@link Page<Profile>}
     */
    @Query(
            value = "" +
                    "SELECT `user`.id, `user`.name, `user`.lastname, `user`.email, `user`.skype, `user`.phone, `user`.room " +
                    "FROM phonebook.`user` " +
                    "JOIN phonebook.`user_projects` " +
                    "ON `user`.`id` = `user_projects`.user_id " +
                    "JOIN phonebook.`project` " +
                    "ON `user_projects`.project_id = `project`.id " +
                    "WHERE `project`.id = ?1 \n#pageable\n",
            countQuery = "" +
                    "SELECT COUNT(*) " +
                    "FROM phonebook.`user` " +
                    "JOIN phonebook.`user_projects` " +
                    "ON `user`.`id` = `user_projects`.user_id " +
                    "JOIN phonebook.`project` " +
                    "ON `user_projects`.project_id = `project`.id " +
                    "WHERE `project`.id = ?1 \n#pageable\n",
            nativeQuery = true)
    Page<Profile> findProfilesByProjectId(Long projectID, Pageable pageable);
}
