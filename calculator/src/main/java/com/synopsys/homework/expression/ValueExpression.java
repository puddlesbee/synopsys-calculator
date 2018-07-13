package com.synopsys.homework.expression;

import com.synopsys.homework.util.Variables;

/**
 * Represents a value expression with an integer value.
 * 
 * @author John
 *
 */
public class ValueExpression implements Expression {
	
	private int value;
	
	public ValueExpression(int value) {
		this.value = value;
	}

	@Override
	public int evaluate(Variables variables) {
		return value;
	}

	@Override
	public String toString() {
		return "ValueExpression [value=" + value + "]";
	}
}
