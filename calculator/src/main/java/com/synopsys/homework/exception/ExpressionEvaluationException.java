package com.synopsys.homework.exception;

/**
 * Exceptions related to evaluation of expressions (missing variables, exceed maximum value, duplicate variables, etc.)
 * 
 * @author John
 *
 */
public class ExpressionEvaluationException extends ExpressionException {

	private static final long serialVersionUID = -2389180720901092592L;
	
	public ExpressionEvaluationException(String msg) {
		super(msg);
	}
}
