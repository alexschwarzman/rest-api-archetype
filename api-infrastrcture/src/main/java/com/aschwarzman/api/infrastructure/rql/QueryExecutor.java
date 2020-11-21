package com.aschwarzman.api.infrastructure.rql;

import java.util.List;

/**
 * 
 * @author aschwarzman
 * @param <T>
 *            Element type of the executor
 * @since Jul 29, 2020
 */
public interface QueryExecutor<T> {

	List<T> execute(QueryContext query);

}
