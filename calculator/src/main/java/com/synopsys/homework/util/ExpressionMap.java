package com.synopsys.homework.util;

import java.util.HashMap;
import java.util.Map;

import com.synopsys.homework.expression.Expression;

public class ExpressionMap {
	
	public static final String EXPRESSION_KEY_PREFIX = "Expr-";

	private Map<String, Expression> map = new HashMap<>();

	private int counter;
	
	public String put(Expression expression) {
		String key = EXPRESSION_KEY_PREFIX + counter;
		map.put(key, expression);
		counter++;
		return key;
	}
	
	public Expression get(String key) {
		return map.get(key);
	}
}
