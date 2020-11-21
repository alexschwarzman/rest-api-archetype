package com.aschwarzman.api.archetype.services;

import com.aschwarzman.api.infrastructure.model.Element;
import com.aschwarzman.api.infrastructure.rql.QueryContext;

import java.util.List;
import java.util.Map;

public interface SearchControllerService<E extends Element<?>> extends ControllerService {

	List<E> search(Map<String, String> headers, QueryContext query);

}
