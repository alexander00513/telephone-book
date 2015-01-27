package com.gmail.bogatyr.alexander.server.guice;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class PersistentInitializer {

    @Inject
    PersistentInitializer(PersistService service) {
        service.start();
    }
}
