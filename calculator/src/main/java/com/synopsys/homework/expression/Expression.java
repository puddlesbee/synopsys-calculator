package com.synopsys.homework.expression;

import com.synopsys.homework.exception.ExpressionEvaluationException;
import com.synopsys.homework.util.Variables;

/**
 * Parent interface for all types of expressions
 * 
 * @author John
 *
 */
public interface Expression {

	int evaluate(Variables variables) throws ExpressionEvaluationException;
}
