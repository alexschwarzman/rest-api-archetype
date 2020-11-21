package com.aschwarzman.api.infrastructure.rql.context.condition;

import java.util.Arrays;

import com.aschwarzman.api.infrastructure.rql.context.QueryPart;
import com.aschwarzman.api.infrastructure.rql.context.condition.logical.And;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class Condition implements QueryPart {

	public Condition add(Condition andditional) {
		return new And(Arrays.asList(this, andditional));
	}

}
