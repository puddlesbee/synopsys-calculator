package com.synopsys.homework.expression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.ExpressionEvaluationException;
import com.synopsys.homework.util.Variables;

/**
 * Represents a let expression that allows an expression's value to be assigned to a variable and used later on by its own expression or other nested exceptions.
 * 
 * @author John
 *
 */
public class LetExpression extends VariableExpression {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Expression firstExpression;
	private Expression secondExpression;
	
	public LetExpression() {}
	
	public LetExpression(String variableName, Expression firstExpression, Expression secondExpression) {
		this.variableName = variableName;
		this.firstExpression = firstExpression;
		this.secondExpression = secondExpression;
	}
	
	public Expression getFirstExpression() {
		return firstExpression;
	}

	public void setFirstExpression(Expression firstExpression) {
		this.firstExpression = firstExpression;
	}

	public Expression getSecondExpression() {
		return secondExpression;
	}

	public void setSecondExpression(Expression secondExpression) {
		this.secondExpression = secondExpression;
	}
	
	@Override
	public int evaluate(Variables variables) throws ExpressionEvaluationException {
		int value = firstExpression.evaluate(variables);
		variables.put(variableName, value);
		logger.info(String.format("Stored Variable: %s Value: %d", variableName, value));
		
		value = secondExpression.evaluate(variables);
		variables.remove(variableName); // remove variable after evaluation of enclosed expressions
		return value;
	}

	@Override
	public String toString() {
		return "LetExpression [variableName=" + variableName + ", firstExpression=" + firstExpression
				+ ", secondExpression=" + secondExpression + "]";
	}
}
