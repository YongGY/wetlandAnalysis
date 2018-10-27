package com.trimble.wetlandAnalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test by JUnit
 * 
 * @author William
 */
public class TestJUnit {

	Wetland test;

	@Test
	public void testInputZero() {

		test = new Wetland();
		String result = ("240000");
		String input = new String("{“”}");

		try {
			test.readInput(input);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return;
		}
		test.clearFarmLandValue();
		test.setWetLandValue();
		test.getArableLands();
		test.printOutput();

		assertEquals(result, test.printOutput());
	}

	@Test
	public void testInputInvalid() throws Exception {
		test = new Wetland();
		String input = new String("{“0.2 -292 399 307”}");
		boolean thrown = false;
		try {
			test.readInput(input);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void testInputCount() throws Exception {
		test = new Wetland();
		String input = new String("{“399 307”}");
		boolean thrown = false;
		try {
			test.readInput(input);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	@Test
	public void testOneWetland() {

		test = new Wetland();
		String result = ("116800 116800");
		String input = new String("{“0 292 399 307”}");

		try {
			test.readInput(input);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return;
		}
		test.clearFarmLandValue();
		test.setWetLandValue();
		test.getArableLands();
		test.printOutput();

		assertEquals(result, test.printOutput());
	}

	@Test
	public void testMulWetland() {

		test = new Wetland();
		String result = ("22816 192608");
		String input = new String("{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}");

		try {
			test.readInput(input);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return;
		}
		test.clearFarmLandValue();
		test.setWetLandValue();
		test.getArableLands();
		test.printOutput();

		assertEquals(result, test.printOutput());
	}

	@Test
	public void testSTDIN() {

		test = new Wetland();
		try {
			test.readFromSTDIN();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return;
		}
		test.clearFarmLandValue();
		test.setWetLandValue();
		test.getArableLands();
		System.out.println(test.printOutput());
	}

}
