package com.sogeti.resources;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class CountryResourceImpl implements CountryResource{
    @Override
    public Resource getResource() {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("countries.csv");
        return resource;
    }
}
