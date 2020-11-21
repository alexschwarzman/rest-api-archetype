package com.aschwarzman.api.archetype.handlers;

import com.aschwarzman.api.archetype.controllers.Controller;
import com.aschwarzman.api.archetype.exceptions.ErrorResponseException;
import com.aschwarzman.api.archetype.exceptions.HttpStatusException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;

public abstract class BaseRequestHandler<C extends Controller> {

	private static Logger LOGGER = LoggerFactory.getLogger(BaseRequestHandler.class);

	private final C controller;

	public BaseRequestHandler(C controller) {
		this.controller = controller;
	}

	protected final ResponseEntity<?> handleCallWithExceptionHandling(OperatorRunner runner) {
		try {
			return runner.run();
		} catch (HttpStatusException e) {
			LOGGER.error("HTTP error (" + e.getStatus() + ") while trying to handle rest request", e);
			BodyBuilder status = ResponseEntity.status(e.getStatus());
			if (e instanceof ErrorResponseException) {
				return status.body(((ErrorResponseException)e).getResponse());
			}
			return status.build();
		} catch (HttpStatusCodeException e) {
			LOGGER.error("HTTP error (" + e.getStatusCode() + " - " + e.getStatusText() + ") while trying to handle rest request", e);
			String responseBodyAsString = e.getResponseBodyAsString();
			if (StringUtils.hasLength(responseBodyAsString)) {
				responseBodyAsString = e.getMessage();
			}
			return new ResponseEntity(responseBodyAsString, e.getResponseHeaders(), e.getStatusCode());
		} catch (Exception e) {
			LOGGER.error("Unexpected error while trying to handle rest request", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	protected C getController() {
		return controller;
	}

	protected static interface OperatorRunner {

		public ResponseEntity<?> run();

	}

}
