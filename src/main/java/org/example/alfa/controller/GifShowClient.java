package org.example.alfa.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gif-show-client", url = "${gif.show.url}")
public interface GifShowClient {

    @GetMapping(path = "/media/{id}/200.gif", produces = MediaType.IMAGE_GIF_VALUE)
    Resource showGif(@PathVariable String id);
}
