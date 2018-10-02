package com.dropwizard.learning.hellowrold.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import com.dropwizard.learning.hellowrold.api.Saying;
import com.google.common.base.Optional;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResources {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResources(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
