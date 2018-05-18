package org.restapi.persistence.service;

import org.restapi.persistence.IOperations;
import org.restapi.persistence.model.Category;

public interface ICategoryService extends IOperations<Category> {

    String retrieveFullName(Long id);
}
