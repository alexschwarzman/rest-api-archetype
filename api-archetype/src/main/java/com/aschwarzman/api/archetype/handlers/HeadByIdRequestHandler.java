package com.aschwarzman.api.archetype.handlers;

import com.aschwarzman.api.archetype.controllers.HeadByIdController;
import com.aschwarzman.api.archetype.services.HeadByIdControllerService;
import com.aschwarzman.api.archetype.utils.ControllerUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Map;

public class HeadByIdRequestHandler<K extends Serializable> extends BaseRequestHandler<HeadByIdController<K, HeadByIdControllerService<K>>> {

	private static Logger LOGGER = LoggerFactory.getLogger(HeadByIdRequestHandler.class);

	public HeadByIdRequestHandler(HeadByIdController controller) {
		super(controller);
	}

	public final ResponseEntity<Void> handle(Map<String, String> headers, K id) {
		return (ResponseEntity<Void>)handleCallWithExceptionHandling(new OperatorRunner() {

			@Override
			public ResponseEntity run() {
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("Handling HEAD BY ID operation");
				}
				boolean exists = exists(headers, id);
				if (exists) {
					return ResponseEntity.ok(HttpEntity.EMPTY);
				} else {
					return ControllerUtils.createNotFoundResponse(id);
				}
			}
		});
	}

	protected boolean exists(Map<String, String> headers, K id) {
		return getController().getControllerService().exists(headers, id);
	}
}
