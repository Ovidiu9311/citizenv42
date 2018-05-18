package org.restapi.persistence.service.impl;

import org.restapi.persistence.dao.ICategoryDao;
import org.restapi.persistence.model.Category;
import org.restapi.persistence.service.ICategoryService;
import org.restapi.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService extends AbstractService<Category> implements ICategoryService {

    @Autowired
    private ICategoryDao dao;

    @Override
    protected PagingAndSortingRepository<Category, Long> getDao() {
        return dao;
    }

    @Override
    public String retrieveFullName(Long id){
        String fullName = "";
        Category category = dao.getOne(id);
        while(category != null) {
            fullName = fullName + category.getName();
            category =  category.getParentId();
        }
        return fullName;
    }

}
