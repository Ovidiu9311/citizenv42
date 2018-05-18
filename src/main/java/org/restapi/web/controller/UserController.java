package org.restapi.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.restapi.persistence.model.User;
import org.restapi.persistence.service.IUserService;
import org.restapi.web.hateoas.event.ResourceCreatedEvent;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IUserService service;

    public UserController() {
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
    public User findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        User resourceById = RestPreconditions.checkFound(service.findOne(id));
        return resourceById;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User create(@RequestBody final User resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final User User = service.create(resource);
        final Long idOfCreatedResource = User.getId();
        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));
        return User;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody final User resource) {
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
