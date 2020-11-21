package com.aschwarzman.api.archetype.handlers;

import com.aschwarzman.api.archetype.controllers.SearchController;
import com.aschwarzman.api.archetype.services.SearchControllerService;
import com.aschwarzman.api.archetype.utils.ControllerUtils;
import com.aschwarzman.api.infrastructure.model.Element;
import com.aschwarzman.api.infrastructure.rql.QueryContext;
import com.aschwarzman.api.infrastructure.rql.RQLParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchRequestHandler<E extends Element<?>> extends BaseRequestHandler<SearchController<E, SearchControllerService<E>>> {

	private static Logger LOGGER = LoggerFactory.getLogger(SearchRequestHandler.class);

	private RQLParser rqlParser;

	public SearchRequestHandler(SearchController controller) {
		super(controller);
		rqlParser = new RQLParser(controller.getControllerService().getFieldAliases());
	}

	public final ResponseEntity<CollectionModel<EntityModel<E>>> handle(Map<String, String> headers, String query) {
		return (ResponseEntity<CollectionModel<EntityModel<E>>>)handleCallWithExceptionHandling(new OperatorRunner() {

			@Override
			public ResponseEntity run() {
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace("Handling SEARCH operation");
				}

				List<E> all = search(headers, query);
				if (CollectionUtils.isEmpty(all)) {
					return ResponseEntity.ok(CollectionModel.empty());
				}

				return ResponseEntity.ok(ControllerUtils.wrap(getController(), all.stream().map(e -> ControllerUtils.toEntityModel(getController(), e)).collect(Collectors.toList())));
			}
		});
	}

	protected List<E> search(Map<String, String> headers, String queryString) {
		QueryContext queryContext = null;
		if (queryString == null) {
			queryContext = new QueryContext();
		} else {
			queryContext = rqlParser.parse(queryString);
		}

		return getController().getControllerService().search(headers, queryContext);
	}
}
