package com.synopsys.homework.calculator;

import com.synopsys.homework.exception.ExpressionException;

/**
 * Interface for different expression calculator implementations (Text, XML, JSON, etc.)
 * 
 * @author John
 *
 */
public interface ExpressionCalculator {

	int evaluate(String stringExpression) throws ExpressionException;
}
