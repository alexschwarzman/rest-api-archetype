package com.aschwarzman.api.archetype.services;

import java.io.Serializable;
import java.util.Map;

import com.aschwarzman.api.archetype.exceptions.ConflictException;
import com.aschwarzman.api.infrastructure.model.Element;

public interface CreateControllerService<K extends Serializable, E extends Element<K>> extends ControllerService {

	K create(Map<String, String> headers, E element) throws ConflictException;

}
