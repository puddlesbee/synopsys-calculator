package com.synopsys.homework.expression.builder;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.util.ExpressionMap;
import com.synopsys.homework.util.ExpressionParameters;

@RunWith(MockitoJUnitRunner.class)
public class ExpressionFactoryTest {
	
	@InjectMocks
	private ExpressionFactory factory = new ExpressionFactory();
	
	@Mock
	private LetExpressionBuilder letExpressionBuilder;
	
	@Mock
	private ArithmeticExpressionBuilder arithmeticExpressionBuilder;
	
	private ExpressionParameters parameters = new ExpressionParameters();
	private ExpressionMap map = new ExpressionMap();
	
	@Test
	public void create_arithmeticExpression() throws Exception {
		parameters.setOperation("Add");
		factory.createExpression(parameters, map);
		verify(arithmeticExpressionBuilder).buildExpression(parameters, map);
		verify(letExpressionBuilder, never()).buildExpression(parameters, map);
	}
	
	@Test
	public void create_letExpression() throws Exception {
		parameters.setOperation("let");
		factory.createExpression(parameters, map);
		verify(arithmeticExpressionBuilder, never()).buildExpression(parameters, map);
		verify(letExpressionBuilder).buildExpression(parameters, map);
	}
	
	@Test(expected = InvalidSyntaxExpressionException.class)
	public void create_invalidOperation() throws InvalidSyntaxExpressionException {
		parameters.setOperation("Mod");
		factory.createExpression(parameters, map);
	}
}
