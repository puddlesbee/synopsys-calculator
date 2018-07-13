package com.synopsys.homework.expression.builder;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.util.Constants;
import com.synopsys.homework.util.ExpressionMap;
import com.synopsys.homework.util.ExpressionParameters;

/**
 * Builds the expression and sub-expressions.
 * 
 * @author John
 *
 */
public class ExpressionFactory {
	
	private static final Logger logger = LogManager.getLogger();
	
	private ExpressionBuilder letExpressionBuilder = new LetExpressionBuilder();
	private ExpressionBuilder arithmeticExpressionBuilder = new ArithmeticExpressionBuilder();
	
	public Expression createExpression(ExpressionParameters parameters, ExpressionMap expressionMap) throws InvalidSyntaxExpressionException {
		String operation = StringUtils.upperCase(parameters.getOperation());
		logger.info(String.format("Creating %s expression.", operation));
	
		Expression expression = null;
		
		switch(operation) {
			case Constants.Operations.LET:
				expression = letExpressionBuilder.buildExpression(parameters, expressionMap);
				break;
			case Constants.Operations.ADDITION:
			case Constants.Operations.SUBTRACTION:
			case Constants.Operations.MULTIPLICATION:
			case Constants.Operations.DIVISION:
				expression = arithmeticExpressionBuilder.buildExpression(parameters, expressionMap);
				break;
			default:
				throw new InvalidSyntaxExpressionException("An unknown operation was parsed from the input: " + operation);
		}
		
		return expression;
	}
}
