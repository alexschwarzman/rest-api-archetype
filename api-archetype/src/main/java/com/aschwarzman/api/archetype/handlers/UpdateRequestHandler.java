package com.aschwarzman.api.archetype.handlers;

import com.aschwarzman.api.archetype.controllers.UpdateController;
import com.aschwarzman.api.archetype.exceptions.NotFoundException;
import com.aschwarzman.api.archetype.services.UpdateControllerService;
import com.aschwarzman.api.archetype.utils.ControllerUtils;
import com.aschwarzman.api.infrastructure.errors.ErrorEntry;
import com.aschwarzman.api.infrastructure.errors.ErrorResponse;
import com.aschwarzman.api.infrastructure.model.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

public class UpdateRequestHandler<K extends Serializable, E extends Element<K>> extends BaseRequestHandler<UpdateController<K, E, UpdateControllerService<K, E>>> {

	private static Logger LOGGER = LoggerFactory.getLogger(UpdateRequestHandler.class);

	public UpdateRequestHandler(UpdateController controller) {
		super(controller);
	}

	public final ResponseEntity<EntityModel<E>> handle(Map<String, String> headers, K id, E element) {
		return (ResponseEntity<EntityModel<E>>)handleCallWithExceptionHandling(new OperatorRunner() {

			@Override
			public ResponseEntity run() {
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("Handling UPDATE operation");
				}

				K elementId = element.getId();
				if (elementId != null && !id.equals(elementId)) {
					return ResponseEntity.badRequest().body(new ErrorResponse(Arrays.asList(new ErrorEntry("id_invalid", "id field in the element doesn't match the id path variable"))));
				}

				try {
					E updated = update(headers, id, element);
					return ResponseEntity.ok(ControllerUtils.toEntityModel(getController(), updated));
				} catch (NotFoundException e) {
					return ControllerUtils.createNotFoundResponse(id);
				}
			}
		});
	}

	protected E update(Map<String, String> headers, K id, E element) throws NotFoundException {
		return getController().getControllerService().update(headers, id, element);
	}
}
