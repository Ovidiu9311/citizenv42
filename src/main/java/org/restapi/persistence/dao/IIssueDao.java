package org.restapi.persistence.dao;

import org.restapi.persistence.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface IIssueDao extends JpaRepository<Issue, Long> {

    @Query("SELECT f FROM Issue f WHERE LOWER(f.name) = LOWER(:name)")
    Issue retrieveByName(@Param("name") String name);
}
