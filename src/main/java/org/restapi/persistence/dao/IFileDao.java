package org.restapi.persistence.dao;

import org.restapi.persistence.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFileDao extends JpaRepository<File, Long> {

    @Query("select u from File u where u.issueId = :issueId")
    List findFilesByIssue(@Param("issueId") Long issueId);
}
