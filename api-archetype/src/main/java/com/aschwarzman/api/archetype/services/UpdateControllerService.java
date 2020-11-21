package com.aschwarzman.api.archetype.services;

import java.io.Serializable;
import java.util.Map;

import com.aschwarzman.api.archetype.exceptions.NotFoundException;
import com.aschwarzman.api.infrastructure.model.Element;

public interface UpdateControllerService<K extends Serializable, E extends Element<K>> extends ControllerService {

	E update(Map<String, String> headers, K id, E element) throws NotFoundException;
	
}
