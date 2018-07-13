package com.synopsys.homework.expression.builder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.expression.ArithmeticExpression;
import com.synopsys.homework.expression.Expression;
import com.synopsys.homework.expression.ValueExpression;
import com.synopsys.homework.expression.VariableExpression;
import com.synopsys.homework.operator.ArithmeticOperator;
import com.synopsys.homework.util.Constants;
import com.synopsys.homework.util.ExpressionMap;
import com.synopsys.homework.util.ExpressionParameters;
import com.synopsys.homework.util.Variables;

public class ArithmeticExpressionBuilderTest {

	private ExpressionFactory builder = new ExpressionFactory();
	private ExpressionMap expressionMap = new ExpressionMap();
	private ExpressionParameters parameters = new ExpressionParameters();
	private Variables variables = new Variables();

	@Test
	public void buildExpression_whenOperands_Values() throws Exception {
		parameters.setOperation(Constants.Operations.ADDITION);
		List<String> literals = Arrays.asList(new String[] { "3", "5"});
		parameters.setParameters(literals);
		Expression expression = builder.createExpression(parameters, expressionMap);
		
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof ArithmeticExpression);
		Assert.assertEquals(8, expression.evaluate(variables));
	}
	
	@Test
	public void buildExpression_whenOperands_Variables() throws Exception {
		parameters.setOperation(Constants.Operations.ADDITION);
		List<String> literals = Arrays.asList(new String[] { "a", "b"});
		parameters.setParameters(literals);
		Expression expression = builder.createExpression(parameters, expressionMap);
		
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof ArithmeticExpression);
		
		variables.put("a", 100);
		variables.put("b", 1);
		
		Assert.assertEquals(101, expression.evaluate(variables));
	}
	
	@Test
	public void buildExpression_whenOperands_Expressions() throws Exception {
		
		String key_1 = expressionMap.put(new ArithmeticExpression(new ValueExpression(5), new ValueExpression(3), ArithmeticOperator.Multiplication));
		String key_2 = expressionMap.put(new VariableExpression("x"));
		
		parameters.setOperation(Constants.Operations.ADDITION);
		List<String> literals = Arrays.asList(new String[] { key_1, key_2 });
		parameters.setParameters(literals);
		Expression expression = builder.createExpression(parameters, expressionMap);
		
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof ArithmeticExpression);
		
		variables.put("x", 100);
		
		Assert.assertEquals(115, expression.evaluate(variables));
	}
	
	@Ignore
	@Test
	public void buildExpression_whenOperands_LetExpressions() throws Exception {
		String key_1 = expressionMap.put(new ArithmeticExpression(new ValueExpression(5), new ValueExpression(3), ArithmeticOperator.Multiplication));
		String key_2 = expressionMap.put(new VariableExpression("x"));
		
		parameters.setOperation(Constants.Operations.ADDITION);
		List<String> literals = Arrays.asList(new String[] { key_1, key_2 });
		parameters.setParameters(literals);
		Expression expression = builder.createExpression(parameters, expressionMap);
		
		Assert.assertNotNull(expression);
		Assert.assertTrue(expression instanceof ArithmeticExpression);
		
		variables.put("x", 100);
		
		Assert.assertEquals(115, expression.evaluate(variables));
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void buildExpression_whenUnknownOperation() throws Exception {
		parameters.setOperation("aDDed");
		List<String> literals = Arrays.asList(new String[] { "a", "b" });
		parameters.setParameters(literals);
		builder.createExpression(parameters, expressionMap);
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void buildExpression_whenNullLiteral() throws Exception {
		parameters.setOperation("aDD");
		List<String> literals = Arrays.asList(new String[] { null, "b" });
		parameters.setParameters(literals);
		builder.createExpression(parameters, expressionMap);
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void buildExpression_whenExcessExpressions() throws Exception {
		parameters.setOperation(Constants.Operations.ADDITION);
		List<String> literals = Arrays.asList(new String[] { "a", "b", "c", "d" });
		parameters.setParameters(literals);
		builder.createExpression(parameters, expressionMap);
	}
}
