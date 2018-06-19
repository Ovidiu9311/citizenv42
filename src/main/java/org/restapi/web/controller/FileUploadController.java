package org.restapi.web.controller;

import org.restapi.persistence.model.File;
import org.restapi.persistence.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/file")
public class FileUploadController {

    @Autowired
    IFileService fileService;

    @CrossOrigin
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void testMethod(@RequestParam MultipartFile file, @RequestParam("issueId") Long issueId) {
        File fileToUpload = new File();
        fileToUpload.setFileExtension(file.getOriginalFilename().split("\\.")[1]);
        fileToUpload.setIssueId(issueId);
        try {
            fileToUpload.setFileByte(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileService.create(fileToUpload);
        // with that all the photo files will have different names
        fileToUpload.setFileName(fileToUpload.getId() + file.getOriginalFilename());
        fileService.update(fileToUpload);
    }

    @CrossOrigin
    @RequestMapping(value="getPhotosUrl/{issueId}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getPhotosUrl(@PathVariable("issueId") final Long issueId){
      List<File> files = fileService.getFilesByIssue(issueId);
        ArrayList<String> filesUrl = new ArrayList<>();
        for(File file : files){
            filesUrl.add("file/image/"+file.getId());
        }
      return filesUrl;
    }

    @CrossOrigin
    @RequestMapping(value="/{issueId}", method = RequestMethod.GET)
    @ResponseBody
    public List<File> getFiles(@PathVariable("issueId") final Long issueId){
        int x= 1;
        return fileService.getFilesByIssue(issueId);
    }

    @CrossOrigin
    @RequestMapping(value="/image/{fileID}", method = RequestMethod.GET,  produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getFile(@PathVariable("fileID") final Long fileID){
        int x= 1;
        return fileService.findOne(fileID).getFileByte();
    }
}
