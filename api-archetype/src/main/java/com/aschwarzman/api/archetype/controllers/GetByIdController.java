package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.GetByIdControllerService;
import com.aschwarzman.api.infrastructure.model.Element;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiParam;

public interface GetByIdController<K extends Serializable, E extends Element<K>, S extends GetByIdControllerService<K, E>> extends Controller<S> {

	@GetMapping(value = "/{id}", headers = HttpHeaders.ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<EntityModel<E>> get(@ApiParam(required = false, hidden = true) @RequestHeader Map<String, String> headers, @PathVariable(value = "id") K id);
}
