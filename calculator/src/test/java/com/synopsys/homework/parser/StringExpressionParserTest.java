package com.synopsys.homework.parser;

import org.junit.Assert;
import org.junit.Test;

import com.synopsys.homework.exception.ExpressionException;
import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.ArithmeticExpression;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.expression.LetExpression;
import com.synopsys.homework.parser.StringExpressionParser;
import com.synopsys.homework.util.Variables;

public class StringExpressionParserTest {
	
	private StringExpressionParser parser = new StringExpressionParser();
	
	@Test
	public void parse_validExpression() throws ExpressionException {
		Expression expression = parser.parse("add(3,5)");
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof ArithmeticExpression);
		Assert.assertEquals(8, expression.evaluate(new Variables()));
	}
	
	@Test
	public void parse_irregularSpacedExpression() throws ExpressionException {
		Expression expression = parser.parse(" add  (        3 ,   5 )      ");
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof ArithmeticExpression);
		Assert.assertEquals(8, expression.evaluate(new Variables()));
	}
	
	@Test
	public void parse_validExpression_Nested() throws ExpressionException {
		Expression expression = parser.parse("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))");
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof LetExpression);
		Assert.assertEquals(40, expression.evaluate(new Variables()));
	}
	
	@Test
	public void parse_validExpression_SameVariableLetter_DifferentCases() throws ExpressionException {
		Expression expression = parser.parse("let(a, let(A, 10, A), let(b, 20, add(a, b)))");
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof LetExpression);
		Assert.assertEquals(30, expression.evaluate(new Variables()));
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void parse_invalidExpression_missingClosingParenthesis() throws ExpressionException {
		Expression expression = parser.parse("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))");
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof LetExpression);
		Assert.assertEquals(40, expression.evaluate(new Variables()));
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void parse_invalidExpression_missingOpeningParenthesis() throws ExpressionException {
		Expression expression = parser.parse("leta, let(b, 10, add(b, b)), let(b, 20, add(a, b))");
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof LetExpression);
		Assert.assertEquals(40, expression.evaluate(new Variables()));
	}
}
