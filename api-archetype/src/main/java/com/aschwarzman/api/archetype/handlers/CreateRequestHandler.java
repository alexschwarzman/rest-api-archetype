package com.aschwarzman.api.archetype.handlers;

import com.aschwarzman.api.archetype.controllers.CreateController;
import com.aschwarzman.api.archetype.exceptions.ConflictException;
import com.aschwarzman.api.archetype.services.CreateControllerService;
import com.aschwarzman.api.archetype.utils.ControllerUtils;
import com.aschwarzman.api.infrastructure.model.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;

public class CreateRequestHandler<K extends Serializable, E extends Element<K>> extends BaseRequestHandler<CreateController<K, E, CreateControllerService<K, E>>> {

	private static Logger LOGGER = LoggerFactory.getLogger(CreateRequestHandler.class);

	public CreateRequestHandler(CreateController<K, E, CreateControllerService<K, E>> controller) {
		super(controller);
	}

	public final ResponseEntity<Void> handle(Map<String, String> headers, E element) {
		return (ResponseEntity<Void>)handleCallWithExceptionHandling(new OperatorRunner() {

			@Override
			public ResponseEntity run() {
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("Handling CREATE operation");
				}
				try {
					K createdId = create(headers, element);
					return ResponseEntity.created(URI.create(ControllerUtils.getSelfRel(getController(), createdId).getHref())).build();
				} catch (ConflictException e) {
					LOGGER.info("Conflict error while trying to create element: {}", element);
					return ResponseEntity.status(HttpStatus.CONFLICT).location(URI.create(ControllerUtils.getSelfRel(getController(), e.getId()).getHref())).build();
				}
			}
		});
	}

	protected K create(Map<String, String> headers, E element) throws ConflictException {
		return getController().getControllerService().create(headers, element);
	}
}
