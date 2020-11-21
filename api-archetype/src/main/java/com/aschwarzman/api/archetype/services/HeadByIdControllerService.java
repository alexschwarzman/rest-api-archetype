package com.aschwarzman.api.archetype.services;

import java.io.Serializable;
import java.util.Map;

public interface HeadByIdControllerService<K extends Serializable> extends ControllerService {

	boolean exists(Map<String, String> headers, K id);

}
