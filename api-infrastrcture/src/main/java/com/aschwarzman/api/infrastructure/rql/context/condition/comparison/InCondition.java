package com.aschwarzman.api.infrastructure.rql.context.condition.comparison;

import com.aschwarzman.api.infrastructure.rql.context.Expression;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class InCondition extends ComparisonCondition {

	public InCondition(String field, Expression argument) {
		super(field, argument);
	}

}
