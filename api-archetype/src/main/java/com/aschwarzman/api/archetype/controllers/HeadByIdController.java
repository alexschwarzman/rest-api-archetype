package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.services.HeadByIdControllerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiParam;

public interface HeadByIdController<K extends Serializable, S extends HeadByIdControllerService<K>> extends Controller<S> {

	@RequestMapping(method = RequestMethod.HEAD, value = "/{id}")
	ResponseEntity<Void> exists(@ApiParam(required = false, hidden = true) @RequestHeader Map<String, String> headers, @PathVariable(value = "id") K id);
}
