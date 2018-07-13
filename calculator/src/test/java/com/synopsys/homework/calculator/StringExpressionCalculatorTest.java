package com.synopsys.homework.calculator;

import org.junit.Assert;
import org.junit.Test;

import com.synopsys.homework.exception.ExpressionEvaluationException;
import com.synopsys.homework.exception.ExpressionException;
import com.synopsys.homework.exception.InvalidSyntaxExpressionException;

public class StringExpressionCalculatorTest {
	
	private StringExpressionCalculator calculator = new StringExpressionCalculator();
	
	@Test
	public void evaluate_example_1() throws ExpressionException {
		int result = calculator.evaluate("add(1,2)");
		Assert.assertEquals(3, result);
	}
	
	@Test
	public void evaluate_example_2() throws ExpressionException {
		int result = calculator.evaluate("add(1, mult(2,3))");
		Assert.assertEquals(7, result);
	}
	
	@Test
	public void evaluate_example_3() throws ExpressionException {
		int result = calculator.evaluate("mult(add(2, 2), div(9, 3))");
		Assert.assertEquals(12, result);
	}
	
	@Test
	public void evaluate_example_4() throws ExpressionException {
		int result = calculator.evaluate("let(a, 5, add(a, a))");
		Assert.assertEquals(10, result);
	}
	
	@Test
	public void evaluate_example_5() throws ExpressionException {
		int result = calculator.evaluate("let(a, 5, let(b, mult(a, 10), add(b, a)))");
		Assert.assertEquals(55, result);
	}
	
	@Test
	public void evaluate_example_6() throws ExpressionException {
		int result = calculator.evaluate("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))");
		Assert.assertEquals(40, result);
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void evaluate_missingParenthesis() throws ExpressionException {
		calculator.evaluate("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))");
	}
	
	@Test(expected = ExpressionEvaluationException.class)
	public void evaluate_missingVariable() throws ExpressionException {
		calculator.evaluate("let(x, let(b, 10, add(b, b)), let(b, 20, add(a, b)))");
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void evaluate_unknownOperation() throws ExpressionException {
		calculator.evaluate("mod(x, let(b, 10, add(b, b)), let(b, 20, add(a, b)))");
	}
}