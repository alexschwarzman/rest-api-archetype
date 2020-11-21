package com.aschwarzman.api.archetype.handlers;

import com.aschwarzman.api.archetype.controllers.GetByIdController;
import com.aschwarzman.api.archetype.services.GetByIdControllerService;
import com.aschwarzman.api.archetype.utils.ControllerUtils;
import com.aschwarzman.api.infrastructure.model.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Map;

public class GetByIdRequestHandler<K extends Serializable, E extends Element<K>> extends BaseRequestHandler<GetByIdController<K, E, GetByIdControllerService<K, E>>> {

	private static Logger LOGGER = LoggerFactory.getLogger(GetByIdRequestHandler.class);

	public GetByIdRequestHandler(GetByIdController controller) {
		super(controller);
	}

	public final ResponseEntity<EntityModel<E>> handle(Map<String, String> headers, K id) {
		return (ResponseEntity<EntityModel<E>>)handleCallWithExceptionHandling(new OperatorRunner() {

			@Override
			public ResponseEntity run() {
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("Handling GET BY ID operation");
				}

				E element = get(headers, id);
				if (element == null) {
					return ControllerUtils.createNotFoundResponse(id);
				}

				return ControllerUtils.createElementResponse(getController(), element);
			}
		});
	}

	protected E get(Map<String, String> headers, K id) {
		return getController().getControllerService().get(headers, id);
	}
}
