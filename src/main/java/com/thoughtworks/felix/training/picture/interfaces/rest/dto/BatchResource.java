package com.thoughtworks.felix.training.picture.interfaces.rest.dto;

import java.util.List;

public class BatchResource<T> {

    private List<T> data;

    private String url;

    public List<T> getData() {
        return data;
    }

    public String getUrl() {
        return url;
    }

    public static Builder builder() {
        return new Builder<>();
    }

    public static class Builder<T> {
        public BatchResource batchResource = new BatchResource();

        public Builder<T> withData(List<T> data) {
            batchResource.data = data;
            return this;
        }

        public Builder<T> withUrl(String url) {
            batchResource.url = url;
            return this;
        }

        public BatchResource build() {
            return batchResource;
        }
    }
}
