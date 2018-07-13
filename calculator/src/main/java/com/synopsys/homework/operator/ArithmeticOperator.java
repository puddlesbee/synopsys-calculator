package com.synopsys.homework.operator;

import com.synopsys.homework.exception.InvalidSyntaxExpressionException;
import com.synopsys.homework.util.Constants.Operations;

public enum ArithmeticOperator {
	
	Addition(Operations.ADDITION) {
		@Override
		public int perform(int firstOperand, int secondOperand) {
			return firstOperand + secondOperand;
		}
	},
	Subtraction(Operations.SUBTRACTION) {
		@Override
		public int perform(int firstOperand, int secondOperand) {
			return firstOperand - secondOperand;
		}
	},
	Multiplication(Operations.MULTIPLICATION) {
		@Override
		public int perform(int firstOperand, int secondOperand) {
			return firstOperand * secondOperand;
		}
	},
	Division(Operations.DIVISION) {
		@Override
		public int perform(int firstOperand, int secondOperand) {
			return firstOperand / secondOperand;
		}
	};
	
	private String operator;
	
	private ArithmeticOperator(String operator) {
		this.operator = operator;
	}
	
	public static ArithmeticOperator identifyOperator(String operator) throws InvalidSyntaxExpressionException {
		for (ArithmeticOperator value : values()) {
			if (value.operator.equalsIgnoreCase(operator)) {
				return value;
			}
		}
		
		throw new InvalidSyntaxExpressionException("An unknown operation was identified: " + operator);
	}
	
	public abstract int perform(int firstOperand, int secondOperand);
}
