package org.example.alfa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GifJSON {

    @JsonProperty("data")
    private GifObj data;

    public String getGifId() {
        return data.getId();
    }

    private static class GifObj {

        @JsonProperty("id")
        private String id;

        public String getId() {
            return id;
        }
    }
}
