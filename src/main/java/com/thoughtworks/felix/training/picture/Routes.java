package com.thoughtworks.felix.training.picture;

import com.thoughtworks.felix.training.picture.domain.Image;

import java.net.URI;

public abstract class Routes {
    private final String baseUri = "http://localhost/";

    public static URI imageUri(Image image) {
        return URI.create("images/" + image.getId());
    }
}
