package com.synopsys.homework.exception;

/**
 * Exception class for all syntax related problems.
 * 
 * @author John
 *
 */
public class InvalidSyntaxExpressionException extends ExpressionException {

	private static final long serialVersionUID = -8197234892339706855L;
	
	public InvalidSyntaxExpressionException(String msg) {
		super(msg);
	}

}
