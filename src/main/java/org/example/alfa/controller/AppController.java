package org.example.alfa.controller;

import org.example.alfa.model.AppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class AppController {

	@Autowired
	private AppModel appModel;

	@GetMapping(path = "/", produces = MediaType.IMAGE_GIF_VALUE)
	public Resource index() {
		return appModel.getResultGif();
	}
}
