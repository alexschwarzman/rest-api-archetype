package com.aschwarzman.api.archetype.controllers;

import com.aschwarzman.api.archetype.handlers.CreateRequestHandler;
import com.aschwarzman.api.archetype.handlers.DeleteRequestHandler;
import com.aschwarzman.api.archetype.handlers.GetByIdRequestHandler;
import com.aschwarzman.api.archetype.handlers.HeadByIdRequestHandler;
import com.aschwarzman.api.archetype.handlers.SearchRequestHandler;
import com.aschwarzman.api.archetype.handlers.UpdateRequestHandler;
import com.aschwarzman.api.archetype.services.DomainControllerService;
import com.aschwarzman.api.infrastructure.model.Element;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Map;

public abstract class BaseDomainController<K extends Serializable, E extends Element<K>, S extends DomainControllerService<K, E>> extends BaseController<S> implements SearchController<E, S>, HeadByIdController<K, S>, GetByIdController<K, E, S>, CreateController<K, E, S>, UpdateController<K, E, S>, DeleteController<K, S> {

	private SearchRequestHandler<E> searchRequestHandler;
	private HeadByIdRequestHandler<K> existsRequestHandler;
	private GetByIdRequestHandler<K, E> getRequestHandler;
	private CreateRequestHandler<K, E> createRequestHandler;
	private UpdateRequestHandler<K, E> updateRequestHandler;
	private DeleteRequestHandler<K> deleteRequestHandler;

	@Override
	protected void doInit() {
		super.doInit();
		searchRequestHandler = new SearchRequestHandler(this);
		existsRequestHandler = new HeadByIdRequestHandler(this);
		getRequestHandler = new GetByIdRequestHandler(this);
		createRequestHandler = new CreateRequestHandler(this);
		updateRequestHandler = new UpdateRequestHandler(this);
		deleteRequestHandler = new DeleteRequestHandler(this);
	}

	@Override
	public ResponseEntity<CollectionModel<EntityModel<E>>> search(Map<String, String> headers, String query) {
		return searchRequestHandler.handle(headers, query);
	}

	@Override
	public ResponseEntity<Void> exists(Map<String, String> headers, K id) {
		return existsRequestHandler.handle(headers, id);
	}

	@Override
	public ResponseEntity<EntityModel<E>> get(Map<String, String> headers, K id) {
		return getRequestHandler.handle(headers, id);
	}

	@Override
	public ResponseEntity<Void> create(Map<String, String> headers, E element) {
		return createRequestHandler.handle(headers, element);
	}

	@Override
	public ResponseEntity<EntityModel<E>> update(Map<String, String> headers, K id, E element) {
		return updateRequestHandler.handle(headers, id, element);
	}

	@Override
	public ResponseEntity<Void> delete(Map<String, String> headers, K id) {
		return deleteRequestHandler.handle(headers, id);
	}

}
