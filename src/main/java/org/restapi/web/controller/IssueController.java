package org.restapi.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Issue findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        Issue resourceById = RestPreconditions.checkFound(service.findOne(id));
        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return resourceById;
    }

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Issue> findAll() {
        List<Issue> issues = new ArrayList<>();
        Issue issue = new Issue();
        issue.setId(1l);
        issue.setCategory(new Category());
        issue.setDescription("Description1");
        issue.setLatitude(new BigDecimal(44.368278));
        issue.setLongitude(new BigDecimal(26.0549761));
        issue.setTitle("Marker1");
        Issue issue2 = new Issue();
        issue2.setId(2l);
        issue2.setCategory(new Category());
        issue2.setDescription("Description2");
        issue2.setLatitude(new BigDecimal(44.4045858));
        issue2.setLongitude(new BigDecimal(26.0671294));
        issue2.setTitle("Marker2");
        issues.add(issue);
        issues.add(issue2);
        return issues;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Issue create(@RequestBody final Issue resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final Issue issue = service.create(resource);
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
