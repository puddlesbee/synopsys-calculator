package com.synopsys.homework.parser;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.Expression;

/**
 * Interface for different expression parser implementations (Pre, Post, XML, JSON, etc.)
 * 
 * @author John
 *
 */
public interface ExpressionParser {

	Expression parse(String stringExpression) throws InvalidSyntaxExpressionException;
}
