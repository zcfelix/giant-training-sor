package com.thoughtworks.felix.training.picture.support;

import java.io.File;
import java.net.URL;

public abstract class TestHelper {
    public static File readFileFrom(String filePath) {
        final URL url = TestHelper.class.getClassLoader().getResource(filePath);
        if (url == null) {
            throw new RuntimeException(String.format("Can not find the file path: %s", filePath));
        }
        final File file = new File(url.getFile());
        return file;
    }
}
