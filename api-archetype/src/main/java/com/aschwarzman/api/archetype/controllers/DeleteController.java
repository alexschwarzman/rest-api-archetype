package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.DeleteControllerService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiParam;

public interface DeleteController<K extends Serializable, S extends DeleteControllerService<K>> extends Controller<S> {

	@DeleteMapping(value = "/{id}", headers = HttpHeaders.ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> delete(@ApiParam(required = false, hidden = true) @RequestHeader Map<String, String> headers, @PathVariable(value = "id") K id);

}
