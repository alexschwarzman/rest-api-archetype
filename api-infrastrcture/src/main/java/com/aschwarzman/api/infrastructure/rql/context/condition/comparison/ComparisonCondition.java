package com.aschwarzman.api.infrastructure.rql.context.condition.comparison;

import com.aschwarzman.api.infrastructure.rql.context.Expression;
import com.aschwarzman.api.infrastructure.rql.context.condition.Condition;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public abstract class ComparisonCondition extends Condition {

	private String field;
	private Object value;

	public ComparisonCondition(String field, Expression value) {
		this.field = field;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

}
