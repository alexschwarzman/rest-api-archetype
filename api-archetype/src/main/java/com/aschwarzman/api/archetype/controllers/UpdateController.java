package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.UpdateControllerService;
import com.aschwarzman.api.infrastructure.model.Element;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiParam;

public interface UpdateController<K extends Serializable, E extends Element<K>, S extends UpdateControllerService<K, E>> extends Controller<S> {

	@PutMapping(value = "/{id}", headers = HttpHeaders.ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<EntityModel<E>> update(@ApiParam(required = false, hidden = true) @RequestHeader Map<String, String> headers, @PathVariable(value = "id") K id, @RequestBody E element);

}
