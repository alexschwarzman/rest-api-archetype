package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.CreateControllerService;
import com.aschwarzman.api.infrastructure.model.Element;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiParam;

public interface CreateController<K extends Serializable, E extends Element<K>, S extends CreateControllerService<K, E>> extends Controller<S> {

	@PostMapping(value = "/", headers = HttpHeaders.ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> create(@ApiParam(required = false, hidden = true) @RequestHeader Map<String, String> headers, @RequestBody E element);

}
