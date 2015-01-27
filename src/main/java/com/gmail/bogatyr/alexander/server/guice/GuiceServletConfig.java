package com.gmail.bogatyr.alexander.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new ServerModule(),
                new DispatchServletModule(),
                new JpaPersistModule("telephonebook-unit"),
                new PersistentModule(),
                new AppHelperModule()
        );
    }
}
