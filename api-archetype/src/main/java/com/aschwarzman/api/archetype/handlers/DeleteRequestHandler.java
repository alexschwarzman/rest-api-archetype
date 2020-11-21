package com.aschwarzman.api.archetype.handlers;

import com.aschwarzman.api.archetype.controllers.DeleteController;
import com.aschwarzman.api.archetype.exceptions.NotFoundException;
import com.aschwarzman.api.archetype.services.DeleteControllerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.Map;

public class DeleteRequestHandler<K extends Serializable> extends BaseRequestHandler<DeleteController<K, DeleteControllerService<K>>> {

	private static Logger LOGGER = LoggerFactory.getLogger(DeleteRequestHandler.class);

	public DeleteRequestHandler(DeleteController controller) {
		super(controller);
	}

	public final ResponseEntity<Void> handle(Map<String, String> headers, K id) {
		return (ResponseEntity<Void>)handleCallWithExceptionHandling(new OperatorRunner() {

			@Override
			public ResponseEntity run() {
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("Handling DELETE operation");
				}

				try {
					boolean deleted = delete(headers, id);
					if (!deleted) {
						LOGGER.warn("Unable to delete resource with id {}", id);
					}
					return ResponseEntity.noContent().build();
				} catch (NotFoundException e) {
					return ResponseEntity.notFound().build();
				}

			}
		});
	}

	protected boolean delete(Map<String, String> headers, K id) throws NotFoundException {
		return getController().getControllerService().delete(headers, id);
	}
}
