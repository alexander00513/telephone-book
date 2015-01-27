package com.gmail.bogatyr.alexander.server.guice;

import com.google.inject.AbstractModule;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class AppHelperModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Mapper.class).to(DozerBeanMapper.class);
    }
}
