package org.restapi.persistence.dao;

import org.restapi.persistence.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICategoryDao extends JpaRepository<Category, Long> {

    @Query("SELECT f FROM Category f WHERE LOWER(f.name) = LOWER(:name)")
    Category retrieveByName(@Param("name") String name);

}
