package com.aschwarzman.api.archetype.services;

import com.aschwarzman.api.infrastructure.model.Element;

import java.io.Serializable;
import java.util.Map;

public interface GetByIdControllerService<K extends Serializable, E extends Element<K>> extends ControllerService {

	E get(Map<String, String> headers, K id);

}
