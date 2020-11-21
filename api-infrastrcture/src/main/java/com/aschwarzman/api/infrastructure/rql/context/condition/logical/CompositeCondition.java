package com.aschwarzman.api.infrastructure.rql.context.condition.logical;

import com.aschwarzman.api.infrastructure.rql.context.condition.Condition;

import java.util.List;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public abstract class CompositeCondition extends Condition {

	private final List<Condition> conditions;

	public CompositeCondition(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [conditions=" + conditions + "]";
	}

}
