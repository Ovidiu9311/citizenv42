package org.restapi.persistence.service.impl;

import java.util.List;

import org.restapi.persistence.dao.IIssueDao;
import org.restapi.persistence.model.Issue;
import org.restapi.persistence.service.IIssueService;
import org.restapi.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional
public class IssueService extends AbstractService<Issue> implements IIssueService {

    @Autowired
    private IIssueDao dao;

    public IssueService() {
        super();
    }

    @Override
    protected PagingAndSortingRepository<Issue, Long> getDao() {
        return dao;
    }


    @Override
    public Issue retrieveByName(final String name) {
        return dao.retrieveByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Issue> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    @Override
    public Page<Issue> findPaginated(Pageable pageable) {
        return dao.findAll(pageable);
    }

}
