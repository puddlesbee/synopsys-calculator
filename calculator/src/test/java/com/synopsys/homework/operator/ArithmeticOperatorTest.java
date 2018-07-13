package com.synopsys.homework.operator;

import org.junit.Assert;
import org.junit.Test;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.util.Constants;

public class ArithmeticOperatorTest {

	@Test
	public void add() {
		Assert.assertEquals(15, ArithmeticOperator.Addition.perform(5, 10));
	}
	
	@Test
	public void subtract() {
		Assert.assertEquals(5, ArithmeticOperator.Subtraction.perform(10, 5));
	}
	
	@Test
	public void multiply() {
		Assert.assertEquals(12, ArithmeticOperator.Multiplication.perform(4, 3));
	}
	
	@Test
	public void divide() {
		Assert.assertEquals(5, ArithmeticOperator.Division.perform(100, 20));
	}
	
	@Test
	public void identifyOperator() throws InvalidSyntaxExpressionException {
		Assert.assertEquals(ArithmeticOperator.Addition, ArithmeticOperator.identifyOperator(Constants.Operations.ADDITION));
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void identifyOperator_invalidOperator() throws InvalidSyntaxExpressionException {
		Assert.assertEquals(ArithmeticOperator.Addition, ArithmeticOperator.identifyOperator("Modulo"));
	}
}
