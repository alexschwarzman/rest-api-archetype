package com.aschwarzman.api.infrastructure.rql.context.condition.logical;

import com.aschwarzman.api.infrastructure.rql.context.condition.Condition;

import java.util.List;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class Or extends CompositeCondition {

	public Or(List<Condition> condition) {
		super(condition);
	}

}
