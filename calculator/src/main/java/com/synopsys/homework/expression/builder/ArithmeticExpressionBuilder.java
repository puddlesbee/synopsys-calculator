package com.synopsys.homework.expression.builder;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.ArithmeticExpression;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.operator.ArithmeticOperator;
import com.synopsys.homework.util.ExpressionMap;
import com.synopsys.homework.util.ExpressionParameters;

/**
 * Constructs arithmetic expressions.
 * 
 * @author John
 *
 */
public class ArithmeticExpressionBuilder extends ExpressionBuilder {
	
	private final static Logger logger = LogManager.getLogger();
	
	public ArithmeticExpressionBuilder() {
		expectedParameters = 2;
	}

	@Override
	protected Expression build(ExpressionParameters parameters, ExpressionMap expressionMap) throws InvalidSyntaxExpressionException {
		ArithmeticExpression expression = new ArithmeticExpression();
		expression.setOperator(ArithmeticOperator.identifyOperator(parameters.getOperation()));
		
		logger.debug("Operation: " + parameters.getOperation());

		List<String> operationParameters = parameters.getOperationParameters();
		
		logger.debug("OperationParameters: " + operationParameters);
		
		expression.setFirstExpression(buildSubExpression(operationParameters.get(0), expressionMap));
		expression.setSecondExpression(buildSubExpression(operationParameters.get(1), expressionMap));
		
		logger.debug("ArithmeticExpression built: " + expression);
		
		return expression;
	}
}
