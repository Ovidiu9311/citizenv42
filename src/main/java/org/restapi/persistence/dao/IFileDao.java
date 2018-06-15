package org.restapi.persistence.dao;

import org.restapi.persistence.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileDao extends JpaRepository<File, Long> {
}
