package com.aschwarzman.api.infrastructure.rql.context;

/**
 * 
 * @author aschwarzman
 * @since Jul 29, 2020
 */
public class ConstantExpression implements Expression {

	private final Object constant;

	public ConstantExpression(Object constant) {
		this.constant = constant;
	}

	public Object getConstant() {
		return constant;
	}

	@Override
	public String toString() {
		return "ConstantExpression [constant=" + constant + "]";
	}

}
