package org.restapi.persistence.service.impl;

import org.restapi.persistence.dao.IFileDao;
import org.restapi.persistence.model.File;
import org.restapi.persistence.service.IFileService;
import org.restapi.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

public class FileService extends AbstractService<File> implements IFileService {

    @Autowired
    private IFileDao dao;

    @Override
    protected PagingAndSortingRepository<File, Long> getDao() {
        return dao;
    }
}
