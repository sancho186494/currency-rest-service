package org.example.alfa.controller;

import org.example.alfa.model.GifJSON;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="gif-api-client", url="${gif.api}")
public interface GifApiClient {

    @GetMapping("/v1/gifs/random")
    GifJSON getRandomGif(@RequestParam String api_key, @RequestParam String tag);

}
