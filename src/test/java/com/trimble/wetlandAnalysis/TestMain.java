package com.trimble.wetlandAnalysis;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test by Runner
 * 
 * @author William
 */
public class TestMain {

	// input value by console
	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(TestJUnit.class);
		for (Failure f : result.getFailures()) {
			System.out.println("Failed. " + f.toString());
		}

		System.out.println("Successfull! " + result.wasSuccessful());
	}

}