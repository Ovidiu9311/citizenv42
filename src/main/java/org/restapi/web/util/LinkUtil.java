package org.restapi.web.util;

import javax.servlet.http.HttpServletResponse;


public final class LinkUtil {

    private LinkUtil() {
        throw new AssertionError();
    }

    public static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

}
