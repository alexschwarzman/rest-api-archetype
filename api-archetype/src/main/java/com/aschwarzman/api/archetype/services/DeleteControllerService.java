package com.aschwarzman.api.archetype.services;

import com.aschwarzman.api.archetype.exceptions.NotFoundException;

import java.io.Serializable;
import java.util.Map;

public interface DeleteControllerService<K extends Serializable> extends ControllerService {

	boolean delete(Map<String, String> headers, K id) throws NotFoundException;

}
