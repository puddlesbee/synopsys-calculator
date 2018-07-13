package com.synopsys.homework.main;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import com.synopsys.homework.calculator.ExpressionCalculator;
import com.synopsys.homework.calculator.StringExpressionCalculator;
import com.synopsys.homework.exception.InvalidSyntaxExpressionException;

/**
 * Main application class that accepts simple mathematical operations via command line arguments.
 * 
 * Example: java -cp <jar> com.synopsys.homework.main.Main "add(2, 2)"
 * 
 * The logging level is set to INFO by default but can be set manually via command line by adding "-Dlog.level=<LOG LEVEL>".
 * 
 * Example: java -Dlog.level=DEBUG -cp <jar> com.synopsys.homework.main.Main "add(2, 2)" 
 * 
 * Logs to the console only.
 * 
 * Assumptions:
 * 1. User will not use the EXACT same variable in nested "Let" substitution expressions. This will result in an error.
 * 
 * 		Example: Let(a, 20, Let(a, 10, add(a, a)))
 * 
 * 2. No arithmetic expressions will result in value outside the MIN and MAX range of an Integer. Values in the input expression will be checked 
 * if it is within the Integer MIN and MAX range. 
 *  
 * Frameworks/Libraries Used:
 * 1. JUnit
 * 2. Log4j2
 * 3. Apache Commons Lang (for String utilities)
 * 4. Mockito
 * 
 * @author John
 *
 */
public class Main {
	
	public static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args) {
		initLogLevels();
		
		if (args.length == 0 || StringUtils.isEmpty(args[0])) {
			System.out.println("Please enter a valid expression. Example: Add(1, 3)");
			System.exit(1);
		}
		
		ExpressionCalculator calculator = new StringExpressionCalculator();
		
		try {
			System.out.println("Output: " + calculator.evaluate(args[0]));
			System.exit(0);
		} catch (InvalidSyntaxExpressionException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("There was a problem processing the expression: " + e.getMessage());
		}
		
		System.exit(1);
	}

	private static void initLogLevels() {
		String strLevel = System.getProperty("log.level");
		
		if (StringUtils.isBlank(strLevel)) {
			return; 
		}
		
		Level logLevel = Level.getLevel(strLevel);
		
		if (logLevel == null) {
			logger.warn(String.format("Unknown log level: %s. Using default log level in log4j2.xml." + strLevel));	
		} else {
			logger.info("Setting log level to: " + logLevel);
			Configurator.setLevel("com.synopsys.homework", logLevel);
		}
	}
}