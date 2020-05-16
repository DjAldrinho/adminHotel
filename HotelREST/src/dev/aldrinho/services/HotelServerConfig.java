package dev.aldrinho.services;


import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/services")
public class HotelServerConfig extends ResourceConfig {

    public HotelServerConfig() {
        register(PersonasService.class);
        register(MultiPartFeature.class);
        register(HotelService.class);
    }
}

