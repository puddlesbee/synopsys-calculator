package com.synopsys.homework.expression;

import org.junit.Assert;
import org.junit.Test;

import com.synopsys.homework.exception.ExpressionEvaluationException;
import com.synopsys.homework.operator.ArithmeticOperator;
import com.synopsys.homework.util.Variables;

public class LetExpressionTest {

	private LetExpression expression;
	
	// let(a, 10, 20) = 20 
	@Test
	public void evaluate_simpleLetExpression_1() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new ValueExpression(10), 
				new ValueExpression(20));
		Assert.assertEquals(20 ,expression.evaluate(new Variables()));
	}
	
	// let(a, 10, add(1, 2)) = 3
	@Test
	public void evaluate_simpleLetExpression_2() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new ValueExpression(10), 
				new ArithmeticExpression(new ValueExpression(1), new ValueExpression(2), ArithmeticOperator.Addition));
		Assert.assertEquals(3 ,expression.evaluate(new Variables()));
	}
	
	// let(a, 10, add(a, 5)) = 3
	@Test
	public void evaluate_simpleLetExpression_3() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new ValueExpression(10), 
				new ArithmeticExpression(new VariableExpression("a"), new ValueExpression(5), ArithmeticOperator.Addition));
		Assert.assertEquals(15 ,expression.evaluate(new Variables()));
	}
	
	// let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)) = 40
	@Test
	public void evaluate_complexLetExpression_1() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new LetExpression("b", new ValueExpression(10), new ArithmeticExpression(new VariableExpression("b"), new VariableExpression("b"), ArithmeticOperator.Addition)), 
				new LetExpression("b", new ValueExpression(20), new ArithmeticExpression(new VariableExpression("a"), new VariableExpression("b"), ArithmeticOperator.Addition)));
		Assert.assertEquals(40 ,expression.evaluate(new Variables()));
	}
	
	// let(a, let(b, 10, b), let(b, 20, b)) = 20
	@Test
	public void evaluate_complexLetExpression_2() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new LetExpression("b", new ValueExpression(10), new VariableExpression("b")), 
				new LetExpression("b", new ValueExpression(20), new VariableExpression("b")));
		Assert.assertEquals(20 ,expression.evaluate(new Variables()));
	}
	
	
	// let(a, 30, let(b, a, add(a, b))) = 20
	@Test
	public void evaluate_complexLetExpression_3() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new ValueExpression(30), 
				new LetExpression("b", new VariableExpression("a"), new ArithmeticExpression(new VariableExpression("a"), new VariableExpression("b"), ArithmeticOperator.Addition)));
		Assert.assertEquals(60 ,expression.evaluate(new Variables()));
	}
	
	// let(a, 30, let(A, 20, add(A, a))) = 50
	@Test
	public void evaluate_complexLetExpression_4() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new ValueExpression(30), 
				new LetExpression("A", new ValueExpression(20), new ArithmeticExpression(new VariableExpression("A"), new VariableExpression("a"), ArithmeticOperator.Addition)));
		Assert.assertEquals(50 ,expression.evaluate(new Variables()));
	}
	
	// let(a, 30, add(a, x)) 
	@Test(expected = ExpressionEvaluationException.class)
	public void evaluate_variableNotExisting() throws ExpressionEvaluationException {
		expression = new LetExpression("a", 
				new ValueExpression(30), 
				new ArithmeticExpression(new VariableExpression("a"), new VariableExpression("x"), ArithmeticOperator.Addition));
		Assert.assertEquals(60 ,expression.evaluate(new Variables()));
	}
}
