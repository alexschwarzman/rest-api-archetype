package com.aschwarzman.api.infrastructure.rql.context.sort;

import com.aschwarzman.api.infrastructure.rql.context.Expression;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class SortPart {

	private Expression expression;
	private boolean asc = true;

	public SortPart(Expression expression, boolean asc) {
		this.expression = expression;
		this.asc = asc;
	}

	public Expression getExpression() {
		return expression;
	}

	public boolean isAsc() {
		return asc;
	}

	@Override
	public String toString() {
		return "SortPart [expression=" + expression + ", asc=" + asc + "]";
	}

}
