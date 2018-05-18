package org.restapi.web.util;

import org.restapi.web.exception.MyResourceNotFoundException;
import org.springframework.http.HttpStatus;

public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError();
    }

    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }
        return resource;
    }
}
