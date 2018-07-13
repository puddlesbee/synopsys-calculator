package com.synopsys.homework.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.ExpressionException;
import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.parser.ExpressionParser;
import com.synopsys.homework.parser.StringExpressionParser;
import com.synopsys.homework.util.Variables;

/**
 * Simple class that accepts mathematical expressions as normal text and evaluates it.
 * 
 * @author John
 *
 */
public class StringExpressionCalculator implements ExpressionCalculator {
	
	private static final Logger logger = LogManager.getLogger();
	
	private ExpressionParser parser;
	
	public StringExpressionCalculator() {
		parser = new StringExpressionParser();
	}
	
	public ExpressionParser getParser() {
		return parser;
	}

	public void setParser(ExpressionParser parser) {
		this.parser = parser;
	}

	@Override
	public int evaluate(String stringExpression) throws ExpressionException {
		
		int result = 0;
		Expression expression = null;
		try {
			expression = parser.parse(stringExpression);
			result = expression.evaluate(new Variables());
		} catch (InvalidSyntaxExpressionException e) {
			logger.error("There was a problem parsing the expression: " + stringExpression, e);
			throw e; 			
		} catch (ExpressionException e) {
			logger.error("There was a problem evaluating the expression: " + stringExpression, e);
			throw e; 
		} catch (Exception e) {
			logger.error("An unexpected problem was encountered during processing. Please see stacktrace for more details.", e);
			throw e;
		}
		
		return result;
	}
}
