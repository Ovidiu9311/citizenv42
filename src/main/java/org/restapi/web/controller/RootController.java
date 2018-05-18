package org.restapi.web.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restapi.web.util.LinkUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping(value = "/")
public class RootController {

    public RootController() {
        super();
    }

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adminRoot(final HttpServletRequest request, final HttpServletResponse response) {
        List<String> uris = new ArrayList<>();
        final URI issueUri = new UriTemplate("{rootUri}/{resource}").expand( "", "issue");
        final String linkToIsuue = LinkUtil.createLinkHeader(issueUri.toASCIIString(), "collection");
        final URI userUri = new UriTemplate("{rootUri}/{resource}").expand( "", "user");
        final String linkToUser = LinkUtil.createLinkHeader(userUri.toASCIIString(), "collection");
        uris.add(linkToIsuue);
        uris.add(linkToUser);
        response.addHeader("Links", uris.toString());
    }

}
