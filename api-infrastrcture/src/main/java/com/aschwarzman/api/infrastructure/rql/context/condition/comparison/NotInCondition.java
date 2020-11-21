package com.aschwarzman.api.infrastructure.rql.context.condition.comparison;

import com.aschwarzman.api.infrastructure.rql.context.Expression;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class NotInCondition extends ComparisonCondition {

	public NotInCondition(String field, Expression argument) {
		super(field, argument);
	}

}
