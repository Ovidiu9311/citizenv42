package org.restapi.persistence.dao;

import org.restapi.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserDao extends JpaRepository<User, Long> {

    @Query("SELECT f FROM Issue f WHERE LOWER(f.name) = LOWER(:name)")
    User retrieveByName(@Param("name") String name);
}
