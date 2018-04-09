package com.thoughtworks.felix.training.picture.support;

import org.springframework.test.context.ActiveProfilesResolver;

public class TestProfileResover implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{"dev"};
    }
}
