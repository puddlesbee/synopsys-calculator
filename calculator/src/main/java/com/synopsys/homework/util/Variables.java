package com.synopsys.homework.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.synopsys.homework.exception.ExpressionEvaluationException;

/**
 * Wrapper for a map instance that stores variable/value pairs.
 * 
 * @author John
 *
 */
public class Variables {
	
	private static final Logger logger = LogManager.getLogger();
	
	private Map<String, Integer> map = new HashMap<>();
	
	public int get(String variableName) throws ExpressionEvaluationException {
		if (map.containsKey(variableName)) {
			int value = map.get(variableName);
			logger.debug(String.format("Variables - Get: %s Value: %d", variableName, value));
			return value;
		} else {
			throw new ExpressionEvaluationException("Variable does not exist: " + variableName);
		}
	}
	
	public void put(String variableName, int value) throws ExpressionEvaluationException {
		if (map.containsKey(variableName)) {
			throw new ExpressionEvaluationException("Variable is already existing: " + variableName);
		} else {
			logger.debug(String.format("Variables - Put: %s Value: %d", variableName, value));
			map.put(variableName, value);
		}
	}
	
	/**
	 * Call whenever let expression has been evaluated.
	 * 
	 * @param variableName
	 */
	public void remove(String variableName) {
		logger.debug(String.format("Variables - Remove: %s", variableName));
		map.remove(variableName);
	}

	@Override
	public String toString() {
		return "Variables [map=" + map + "]";
	}
}
