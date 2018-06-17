package org.restapi.persistence.service;

import org.restapi.persistence.IOperations;
import org.restapi.persistence.model.File;

import java.util.List;

public interface IFileService extends IOperations<File> {

     List<File> getFilesByIssue(Long issueId);
}
