package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.SearchControllerService;
import com.aschwarzman.api.infrastructure.model.Element;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import io.swagger.annotations.ApiParam;

public interface SearchController<E extends Element<?>, S extends SearchControllerService<E>> extends Controller<S> {

	@GetMapping(headers = HttpHeaders.ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<CollectionModel<EntityModel<E>>> search(@ApiParam(required = false, hidden = true) @RequestHeader Map<String, String> headers,
			@RequestParam(name = "query", required = false) String query);

}
