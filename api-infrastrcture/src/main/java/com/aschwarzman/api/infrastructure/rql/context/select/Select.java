package com.aschwarzman.api.infrastructure.rql.context.select;

import com.aschwarzman.api.infrastructure.rql.context.Expression;
import com.aschwarzman.api.infrastructure.rql.context.QueryPart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class Select implements QueryPart {

	private final List<Expression> expressions;

	public Select(List<Expression> expressions) {
		if (expressions == null) {
			this.expressions = Collections.emptyList();
		} else {
			this.expressions = new ArrayList(expressions);
		}
	}

	public List<Expression> getExpressions() {
		return expressions;
	}

}
