package org.restapi.persistence.service;

import org.restapi.persistence.IOperations;
import org.restapi.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService extends IOperations<User> {

    //User retrieveByName(String name);

    Page<User> findPaginated(Pageable pageable);

}
