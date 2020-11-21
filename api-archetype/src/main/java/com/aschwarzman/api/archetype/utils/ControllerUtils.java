package com.aschwarzman.api.archetype.utils;

import com.aschwarzman.api.archetype.controllers.Controller;
import com.aschwarzman.api.archetype.controllers.GetByIdController;
import com.aschwarzman.api.archetype.controllers.SearchController;
import com.aschwarzman.api.infrastructure.model.Element;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ControllerUtils {

	public static <E extends Element> EntityModel<E> toEntityModel(Controller controller, E element) {
		EntityModel<E> entityModel = EntityModel.of(element);
		Link location = getSelfRel(controller, element.getId());
		if (location != null) {
			entityModel.add(location);
		}
		if (controller instanceof SearchController) {
			SearchController searchController = (SearchController)controller;
			entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn((searchController).getClass()).search(Collections.emptyMap(), null)).withRel("all"));
		}
		return entityModel;
	}

	public static <K extends Serializable> Link getSelfRel(Controller controller, K id) {
		if (controller instanceof GetByIdController) {
			GetByIdController getByIdController = (GetByIdController)controller;
			return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn((getByIdController).getClass()).get(Collections.emptyMap(), id.toString())).withSelfRel();
		}
		return null;
	}

	public static CollectionModel wrap(Controller controller, List<EntityModel> elementModels) {
		EntityModel prevElementModel = null;
		for (EntityModel elementModel : elementModels) {
			if (prevElementModel != null) {
				Link selfLink = null;
				Optional<Link> selfLinkProvider = elementModel.getLink(IanaLinkRelations.SELF);
				if (selfLinkProvider.isPresent()) {
					selfLink = selfLinkProvider.get();
				}

				Link preSelfLink = null;
				Optional<Link> preSelfLinkProvider = prevElementModel.getLink(IanaLinkRelations.SELF);
				if (preSelfLinkProvider.isPresent()) {
					preSelfLink = preSelfLinkProvider.get();
				}
				if (preSelfLink != null) {
					elementModel.add(Link.of(preSelfLink.getHref(), IanaLinkRelations.PREV));
				}
				if (selfLink != null) {
					prevElementModel.add(Link.of(selfLink.getHref(), IanaLinkRelations.NEXT));
				}
			}
		}
		CollectionModel<EntityModel> collectionModel = CollectionModel.of(elementModels);
		if (controller instanceof SearchController) {
			SearchController searchController = (SearchController)controller;
			collectionModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn((searchController).getClass()).search(Collections.emptyMap(), null)).withRel("all"));
		}
		return collectionModel;
	}

	public static <E extends Element> ResponseEntity<EntityModel<E>> createElementResponse(Controller controller, E element) {
		EntityModel<E> entityModel = toEntityModel(controller, element);

		MultiValueMap<String, String> responseHeaders = new HttpHeaders();
		Optional<Link> selfLinkProvider = entityModel.getLink(IanaLinkRelations.SELF);
		if (selfLinkProvider.isPresent()) {
			String href = selfLinkProvider.get().getHref();
			if (href != null) {
				responseHeaders.add(HttpHeaders.LOCATION, href);
			}
		}
		return new ResponseEntity(entityModel, responseHeaders, HttpStatus.OK);
	}

	public static <K extends Serializable> ResponseEntity createNotFoundResponse(K id) {
		return new ResponseEntity("Element with id " + id + " not found", HttpStatus.NOT_FOUND);
	}
}
