package com.synopsys.homework.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Constants for literals used to represent a string expression for the calculator.
 * 
 * @author John
 *
 */
public class Constants {

	public static final String CLOSING_PARENTHESIS = ")";
	public static final String OPENING_PARENTHESIS = "(";
	public static final String COMMA = ",";
	
	public static final class Operations {
		public static final String ADDITION = "ADD";
		public static final String SUBTRACTION = "SUB";
		public static final String MULTIPLICATION = "MULT";
		public static final String DIVISION = "DIV";
		
		public static final String LET = "LET";
		
		public static final Set<String> ARITHMETIC_OPERATIONS = new HashSet<>(Arrays.asList(new String[] { ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION }));
	}
}
