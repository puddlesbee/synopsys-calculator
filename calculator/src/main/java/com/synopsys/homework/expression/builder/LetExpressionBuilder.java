package com.synopsys.homework.expression.builder;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.expression.LetExpression;
import com.synopsys.homework.util.ExpressionMap;
import com.synopsys.homework.util.ExpressionParameters;

/**
 * Constructs Let Expression instances. Does not use the builder pattern.
 * 
 * @author John
 *
 */
public class LetExpressionBuilder extends ExpressionBuilder {
	
	private final static Logger logger = LogManager.getLogger();
	
	public LetExpressionBuilder() {
		expectedParameters = 3;
	}
	
	@Override
	protected Expression build(ExpressionParameters parameters, ExpressionMap expressionMap) throws InvalidSyntaxExpressionException {
		LetExpression expression = new LetExpression();
		
		List<String> operationParameters = parameters.getOperationParameters();
		
		logger.debug("OperationParameters: " + operationParameters);
		
		String variableName = operationParameters.get(0);
		if (!isVariable(variableName)) {
			throw new InvalidSyntaxExpressionException("Invalid variable name used. Please use character from a-z or A-Z.");
		}
		expression.setVariableName(variableName);
		
		expression.setFirstExpression(buildSubExpression(operationParameters.get(1), expressionMap));
		expression.setSecondExpression(buildSubExpression(operationParameters.get(2), expressionMap));
		
		logger.debug("LetExpression built: " + expression);
		
		return expression;
	}
}