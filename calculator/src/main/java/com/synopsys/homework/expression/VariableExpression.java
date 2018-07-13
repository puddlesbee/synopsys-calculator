package com.synopsys.homework.expression;

import com.synopsys.homework.exception.ExpressionEvaluationException;
import com.synopsys.homework.util.Variables;

/**
 * Represents a variable expression that will fetch its value from memory.
 * 
 * @author John
 *
 */
public class VariableExpression implements Expression {
	
	protected String variableName;
	
	public String getVariableName() {
		return variableName;
	}
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public VariableExpression() {}

	public VariableExpression(String variableName) {
		this.variableName = variableName;
	}
	
	@Override
	public int evaluate(Variables variables) throws ExpressionEvaluationException {
		return variables.get(getVariableName());
	}

	@Override
	public String toString() {
		return "VariableExpression [variableName=" + variableName + "]";
	}
}
