package com.synopsys.homework.expression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.synopsys.homework.exception.ExpressionEvaluationException;
import com.synopsys.homework.operator.ArithmeticOperator;
import com.synopsys.homework.util.Variables;

public class ArithmeticExpressionTest {

	private ArithmeticExpression expression;
	
	private Variables variables = new Variables();
	
	@Before
	public void init() throws Exception {
		variables.put("x", 20);
		variables.put("y", 10);
	}
	
	@Test
	public void performAddition_bothValueExpression() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new ValueExpression(10), new ValueExpression(10), ArithmeticOperator.Addition);
		Assert.assertEquals(20 ,expression.evaluate(variables));
	}
	
	@Test
	public void performAddition_oneValueExpression_oneVariableExpression() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new ValueExpression(10), new VariableExpression("x"), ArithmeticOperator.Addition);
		Assert.assertEquals(30 ,expression.evaluate(variables));
	}
	
	@Test
	public void performAddition_bothVariableExpression() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new VariableExpression("y"), new VariableExpression("x"), ArithmeticOperator.Addition);
		Assert.assertEquals(30 ,expression.evaluate(variables));
	}
	
	@Test
	public void performAddition_oneVariableExpression_oneArithmeticExpression() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new VariableExpression("y"), 
				new ArithmeticExpression(new ValueExpression(3), new ValueExpression(10), ArithmeticOperator.Addition), 
				ArithmeticOperator.Addition);
		Assert.assertEquals(23 ,expression.evaluate(variables));
	}
	
	@Test
	public void performAddition_oneVariableExpression_oneLetExpression() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new VariableExpression("y"), 
				new LetExpression("a" , new ValueExpression(3), new ArithmeticExpression(new VariableExpression("a"), new VariableExpression("a"), ArithmeticOperator.Addition)), 
				ArithmeticOperator.Addition);
		Assert.assertEquals(16 ,expression.evaluate(variables));
	}
	
	@Test
	public void performAddition() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new ValueExpression(10), new ValueExpression(10), ArithmeticOperator.Addition);
		Assert.assertEquals(20 ,expression.evaluate(variables));
	}
	
	@Test
	public void performSubtraction() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new ValueExpression(10), new ValueExpression(10), ArithmeticOperator.Subtraction);
		Assert.assertEquals(0 ,expression.evaluate(variables));
	}
	
	@Test
	public void performMultiplication() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new ValueExpression(10), new ValueExpression(10), ArithmeticOperator.Multiplication);
		Assert.assertEquals(100 ,expression.evaluate(variables));
	}
	
	@Test
	public void performDivision() throws ExpressionEvaluationException {
		expression = new ArithmeticExpression(new ValueExpression(10), new ValueExpression(10), ArithmeticOperator.Division);
		Assert.assertEquals(1 ,expression.evaluate(variables));
	}
}
