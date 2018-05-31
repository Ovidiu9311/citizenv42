package org.restapi.persistence.service.impl;

import com.google.common.collect.Lists;
import org.restapi.persistence.dao.IUserDao;
import org.restapi.persistence.model.User;
import org.restapi.persistence.service.IUserService;
import org.restapi.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService extends AbstractService<User> implements IUserService {

@Autowired
private IUserDao dao;

public UserService() {
        super();
}

@Override
protected PagingAndSortingRepository<User, Long> getDao() {
        return dao;
}


//@Override
//public User retrieveByName(final String name) {
//        return dao.retrieveByName(name);
//}

@Override
@Transactional(readOnly = true)
public List<User> findAll() {
        return Lists.newArrayList(getDao().findAll());
        }

@Override
public Page<User> findPaginated(Pageable pageable) {
        return dao.findAll(pageable);
        }
}
