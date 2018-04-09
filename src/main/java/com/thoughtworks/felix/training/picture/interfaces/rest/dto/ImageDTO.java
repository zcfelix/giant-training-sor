package com.thoughtworks.felix.training.picture.interfaces.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.thoughtworks.felix.training.picture.domain.Image;

public class ImageDTO {
    private String name;
    private String url;

    @JsonCreator
    private ImageDTO() {}

    public ImageDTO(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ImageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public ImageDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public ImageDTO fromDomain(Image image) {
        return new ImageDTO().setName(image.getName()).setUrl("images/" + image.getId());
    }
}
