package com.synopsys.homework.parser;

import static com.synopsys.homework.util.Constants.CLOSING_PARENTHESIS;
import static com.synopsys.homework.util.Constants.COMMA;
import static com.synopsys.homework.util.Constants.OPENING_PARENTHESIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.expression.builder.ExpressionFactory;
import com.synopsys.homework.util.Constants.Operations;
import com.synopsys.homework.util.ExpressionMap;
import com.synopsys.homework.util.ExpressionParameters;

/**
 * Parses the input string into actual expressions.
 * 
 * @author John
 *
 */
public class StringExpressionParser implements ExpressionParser {
	
	private static final Logger logger = LogManager.getLogger();
	private static final String REGEX_SPLIT = "((?<=,|\\(|\\))|(?=,|\\(|\\)))";
	
	private static final Set<String> VALID_CHARS = new HashSet<>(Arrays.asList(new String[] { OPENING_PARENTHESIS, CLOSING_PARENTHESIS, COMMA }));

	private ExpressionFactory builder = new ExpressionFactory();
	
	public ExpressionFactory getBuilder() {
		return builder;
	}

	public void setBuilder(ExpressionFactory builder) {
		this.builder = builder;
	}

	@Override
	public Expression parse(String stringExpression) throws InvalidSyntaxExpressionException {
		Stack<String> literalStack = new Stack<>();
		ExpressionMap expressionMap = new ExpressionMap();
		
		logger.info("Parsing input: " + stringExpression);
		String[] literals = stringExpression.split(REGEX_SPLIT);
		
		for (String literal : literals) {
			literal = StringUtils.trimToNull(literal);

			if (StringUtils.isBlank(literal)) {
				continue;
			}
			
			if (isAllowedLiteral(literal)) {
				if (literal.equalsIgnoreCase(CLOSING_PARENTHESIS)) {
					ExpressionParameters parameters = createExpressionParameters(literalStack);
					Expression expression = builder.createExpression(parameters, expressionMap);
					
					String key = expressionMap.put(expression);
					literalStack.push(key);
				} else {
					logger.debug("Parsed literal: " + literal);
					literalStack.push(literal);
				}
			} else {
				throw new InvalidSyntaxExpressionException("Unknown character parsed from input: " + literal);
			}
		}
		
		Expression finalExpression = expressionMap.get(literalStack.pop()); // final String to pop would be the key to the final expression
		
		if (!literalStack.isEmpty()) {
			throw new InvalidSyntaxExpressionException("Expression is missing a closing parenthesis.");
		}
		
		return finalExpression;
	}

	private ExpressionParameters createExpressionParameters(Stack<String> literalStack) throws InvalidSyntaxExpressionException {
		ExpressionParameters parameters = new ExpressionParameters();
		
		List<String> operationParameters = new ArrayList<>();
		String literal = null;
		boolean found = false;
		
		while (!literalStack.isEmpty()) {
			literal = literalStack.pop();
			
			if (literal.equals(OPENING_PARENTHESIS) && !literalStack.isEmpty()) {
				parameters.setOperation(StringUtils.upperCase(literalStack.pop()));
				found = true;
				break;
			} else if (!literal.equals(COMMA)) {
				operationParameters.add(literal);
			}	
		}
		
		if (!found) {
			throw new InvalidSyntaxExpressionException("Expression is missing an opening parenthesis.");
		}
		
		Collections.reverse(operationParameters);
		parameters.setParameters(operationParameters);
		return parameters;
	}
	
	/**
	 * Validates the literal and checks if it belongs to the list of allowed characters for the expression. Not a big fan of this method.
	 * 
	 * @param literal
	 * @return
	 */
	private boolean isAllowedLiteral(String literal) {
		return  Operations.ARITHMETIC_OPERATIONS.contains(literal.toUpperCase()) || Operations.LET.equalsIgnoreCase(literal.toUpperCase()) || 
				VALID_CHARS.contains(literal) || isVariable(literal) || StringUtils.isNumeric(literal);
	}
	
	private boolean isVariable(String literal) {
		return literal.length() == 1 && CharUtils.isAsciiAlpha(literal.charAt(0));
	}
}
