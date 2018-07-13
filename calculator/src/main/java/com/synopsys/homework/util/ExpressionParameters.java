package com.synopsys.homework.util;

import java.util.List;

/**
 * Contains all the data that will be used to create an expression 
 * 
 * @author John
 *
 */
public class ExpressionParameters {

	private String operation;
	private List<String> operationParameters;
	private ExpressionMap map;
	
	public ExpressionParameters() {}
	
	public ExpressionParameters(String operation, List<String> parameters, ExpressionMap map) {
		this.operation = operation;
		this.operationParameters = parameters;
		this.map = map;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public List<String> getOperationParameters() {
		return operationParameters;
	}
	public void setParameters(List<String> parameters) {
		this.operationParameters = parameters;
	}
	public ExpressionMap getMap() {
		return map;
	}
	public void setMap(ExpressionMap map) {
		this.map = map;
	}
}
