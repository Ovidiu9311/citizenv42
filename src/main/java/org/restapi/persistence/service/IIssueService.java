package org.restapi.persistence.service;

import org.restapi.persistence.IOperations;
import org.restapi.persistence.model.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IIssueService extends IOperations<Issue> {

    Issue retrieveByName(String name);
    
    Page<Issue> findPaginated(Pageable pageable);

}
