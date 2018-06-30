package org.restapi.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.restapi.persistence.dto.IssueDto;
import org.restapi.persistence.model.Category;
import org.restapi.persistence.model.Issue;
import org.restapi.persistence.service.IIssueService;
import org.restapi.web.exception.MyResourceNotFoundException;
import org.restapi.web.hateoas.event.ResourceCreatedEvent;
import org.restapi.web.hateoas.event.SingleResourceRetrievedEvent;
import org.restapi.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Preconditions;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/issue")
public class IssueController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IIssueService service;

    public IssueController() {
        super();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long count() {
        return 2l;
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Issue findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        Issue resourceById = RestPreconditions.checkFound(service.findOne(id));
        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return resourceById;
    }

    @CrossOrigin
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Issue> findAll() {
        List<Issue> issues = service.findAll();
        return issues;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Issue create(@RequestBody IssueDto issueDto, HttpServletResponse response) {
        Preconditions.checkNotNull(issueDto);
        Issue issue = new Issue();
        issue.setTitle(issueDto.getTitle());
        issue.setDescription(issueDto.getDescription());
        issue.setLatitude(issueDto.getLatitude());
        issue.setLongitude(issueDto.getLongitude());
        service.create(issue);
        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, issue.getId()));
        return issue;

    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update( @RequestBody final Issue resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(service.findOne(resource.getId()));
        service.update(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    @ResponseStatus(HttpStatus.OK)
    public void head(final HttpServletResponse resp) {
        resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        resp.setHeader("HeaderParam", "HeaderForHeadMethod");
    }

}
