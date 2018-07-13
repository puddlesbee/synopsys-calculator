package com.synopsys.homework.expression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.ExpressionEvaluationException;
import com.synopsys.homework.operator.ArithmeticOperator;
import com.synopsys.homework.util.Variables;

/**
 * Represents arithmetic expressions that has two operands and an arithmetic operator. 
 * 
 * @author John
 *
 */
public class ArithmeticExpression implements Expression {
	
	private static final Logger logger = LogManager.getLogger();

	private Expression firstOperand;
	private Expression secondOperand;

	private ArithmeticOperator operator;
	
	public ArithmeticExpression() {}

	public ArithmeticExpression(Expression firstOperand, Expression secondOperand, ArithmeticOperator operator) {
		this.firstOperand = firstOperand;
		this.secondOperand = secondOperand;
		this.operator = operator;
	}

	public Expression getFirstOperand() {
		return firstOperand;
	}

	public void setFirstExpression(Expression firstOperand) {
		this.firstOperand = firstOperand;
	}

	public Expression getSecondOperand() {
		return secondOperand;
	}

	public void setSecondExpression(Expression secondOperand) {
		this.secondOperand = secondOperand;
	}

	public ArithmeticOperator getOperator() {
		return operator;
	}

	public void setOperator(ArithmeticOperator operator) {
		this.operator = operator;
	}
	
	@Override
	public int evaluate(Variables variables) throws ExpressionEvaluationException {
		int first = firstOperand.evaluate(variables);
		int second = secondOperand.evaluate(variables);
		
		logger.info("Variables: " + variables);
		logger.info(String.format("Operation: %s First Operand: %d Second Operand: %d", operator, first, second));
		
		return operator.perform(first, second);
	}

	@Override
	public String toString() {
		return "ArithmeticExpression [firstOperand=" + firstOperand + ", secondOperand=" + secondOperand + ", operator="
				+ operator + "]";
	}
}
