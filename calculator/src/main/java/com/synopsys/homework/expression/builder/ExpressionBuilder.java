package com.synopsys.homework.expression.builder;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.expression.ValueExpression;
import com.synopsys.homework.expression.VariableExpression;
import com.synopsys.homework.util.ExpressionMap;
import com.synopsys.homework.util.ExpressionParameters;

public abstract class ExpressionBuilder {

	protected int expectedParameters;
	
	public Expression buildExpression(ExpressionParameters parameters, ExpressionMap expressionMap) throws InvalidSyntaxExpressionException {
		validateParameterCount(parameters);
		return build(parameters, expressionMap);
	}
	
	protected abstract Expression build(ExpressionParameters parameters, ExpressionMap expressionMap) throws InvalidSyntaxExpressionException;
	
	protected Expression buildSubExpression(String literal, ExpressionMap expressionMap) throws InvalidSyntaxExpressionException {
		if (literal == null) {
			throw new InvalidSyntaxExpressionException("A null literal was parsed from the input.");
		}
		
		if (StringUtils.isNumeric(literal)) {
			long value = Long.parseLong(literal);
			
			if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
				throw new InvalidSyntaxExpressionException(String.format("Only values between %d and %d are accepted by the calculator.", 
						Integer.MIN_VALUE, Integer.MAX_VALUE));
			}
			
			return new ValueExpression(Integer.parseInt(literal));
		} else if (isVariable(literal)) {
			return new VariableExpression(literal);
		} else if (literal.startsWith(ExpressionMap.EXPRESSION_KEY_PREFIX)) {
			return expressionMap.get(literal);
		} else {
			throw new InvalidSyntaxExpressionException("An unknown literal was parsed from the input: " + literal);
		}
	}
	
	protected boolean isVariable(String literal) {
		return literal.length() == 1 && CharUtils.isAsciiAlpha(literal.charAt(0));
	}
	
	protected void validateParameterCount(ExpressionParameters parameters) throws InvalidSyntaxExpressionException {
		if (parameters.getOperationParameters().size() != expectedParameters) {
			throw new InvalidSyntaxExpressionException(String.format("An incorrect number of parameters were sent for the %s expression.", parameters.getOperation()));
		}
	}	
}
