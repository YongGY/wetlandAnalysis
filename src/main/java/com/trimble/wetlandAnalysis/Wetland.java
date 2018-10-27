package com.trimble.wetlandAnalysis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

/**
 * Wetland Analysis
 * 
 * @author William
 */
public class Wetland {

	private int farmLand[][];
	private final static int X_LEN = 400;
	private final static int Y_LEN = 600;
	private HashMap<Integer, Integer> areaMap;

	// store wetland coordinates
	private LinkedList<Integer[]> allRectangles;

	// create queue to hold coordinates while iterating to find arable land
	private LinkedList<Integer[]> queueLand;

	public Wetland() {
		allRectangles = new LinkedList<Integer[]>();
		queueLand = new LinkedList<Integer[]>();
		areaMap = new HashMap<Integer, Integer>();
		farmLand = new int[X_LEN][Y_LEN];
	}

	/**
	 * Read the data, parse and verify the format, quantity, and range of the data.
	 * 
	 * @param String inval
	 * @throws Exception
	 */
	public void readInput(String inval) throws Exception {
		inval = inval.substring(1, inval.length() - 1).replaceAll("\"", "").replaceAll("“|”", "");
		String[] strs = inval.split(", ");
		for (String s : strs) {
			if (!s.isEmpty()) {
				String[] coo = s.split(" ");
				if (coo.length < 4) {
					throw new ArrayIndexOutOfBoundsException("It must contain 4 numbers.");
				}

				Integer c0, c1, c2, c3;
				try {
					c0 = Integer.parseInt(coo[0]);
					c1 = Integer.parseInt(coo[1]);
					c2 = Integer.parseInt(coo[2]);
					c3 = Integer.parseInt(coo[3]);
				} catch (NumberFormatException e) {
					throw new NumberFormatException("The coordinates must be numbers.");
				}

				if (c0 >= 0 && c1 >= 0 && c2 >= 0 && c3 >= 0 && c0 < X_LEN && c2 < X_LEN && c1 < Y_LEN && c3 < Y_LEN) {
					allRectangles.add(new Integer[] { c0, c1, c2, c3 });
				} else {
					throw new Exception("The coordinates are not in range.");
				}
			}
		}
	}

	/**
	 * Set 1 in the wetland rectangle
	 */
	public void setWetLandValue() {
		ListIterator<Integer[]> iterator = allRectangles.listIterator();
		while (iterator.hasNext()) {
			Integer[] rectangle = iterator.next();
			for (int i = rectangle[0]; i <= rectangle[2]; i++) {
				for (int j = rectangle[1]; j <= rectangle[3]; j++) {
					farmLand[i][j] = 1;
				}
			}
		}
	}

	/**
	 * Set 0 in the farmLand
	 */
	public void clearFarmLandValue() {
		for (int i = 0; i < X_LEN; i++) {
			for (int j = 0; j < Y_LEN; j++) {
				farmLand[i][j] = 0;
			}
		}
	}

	/**
	 * Add nodes to the queue to be checked
	 * 
	 * @param i
	 * @param j
	 */
	public void addQueueLand(int i, int j) {
		if (farmLand[i][j] == 0) {
			queueLand.add(new Integer[] { i, j });
		}
	}

	/**
	 * Output all the arable land area in square meters, sorted from smallest area
	 * to greatest, separated by a space.
	 * 
	 * @return String output
	 */
	public String printOutput() {
		int[] res = new int[areaMap.values().size()];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : areaMap.entrySet()) {
			res[i] = entry.getValue();
			i++;
		}
		Arrays.sort(res);
		return (Arrays.toString(res)).replaceAll("\\[|\\]|,", "");

	}

	/**
	 * Read input from STDIN.
	 * 
	 * @throws Exception
	 */
	public void readFromSTDIN() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		readInput(s);
	}

	/**
	 * The main core logic. Judging by quere. We will traverse and check how many
	 * nodes Visit all coordinates and find arable land area.
	 */
	public void getArableLands() {
		int land = 1;
		int i = 0;
		int j = 0;

		while (i < X_LEN && j < Y_LEN) {

			// quere is Empty
			if (queueLand.isEmpty()) {
				Integer node[] = { i, j };

				// If node[i][j] has not been visited add to queue
				if (farmLand[i][j] == 0) {
					land++;
					areaMap.put(land, 0);
					queueLand.add(node);
				}
				// Make sure we verify all the land
				if (i == (X_LEN - 1)) {
					i = 0;
					j++;
				} else {
					i++;
				}
			}

			// quere is not Empty
			if (!queueLand.isEmpty()) {
				Integer node[] = queueLand.pop();

				int x = node[0];
				int y = node[1];

				// If node[i][j] has not been visited add to queue
				if (farmLand[x][y] == 0) {
					if (x > 0)
						addQueueLand(x - 1, y);
					if (x < (X_LEN - 1))
						addQueueLand(x + 1, y);
					if (y > 0)
						addQueueLand(x, y - 1);
					if (y < (Y_LEN - 1))
						addQueueLand(x, y + 1);

					farmLand[x][y] = land;
					areaMap.put(land, (areaMap.get(land) + 1));
				}
			}
		}
	}

	public static void main(String[] args) {
		Wetland test = new Wetland();
		// String result = ("240000");
//			String input = new String("{“”}");
//			String input = new String("{“0 292 399 307”}");
		String input = new String("{\"0 0 0 599\", \"2 0 2 599\", \"3 2 399 3\", \"5 0 5 1\"}");

		try {
			test.readInput(input);
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
