package com.success;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReserveSeats {

	/**
	 * Key is row number .. value List<Integer> is the partition information
	 */
	private Map<Integer, List<Integer>> layoutMap = new HashMap<>();
	/**
	 * seat requests
	 */
	private List<String> requests = new ArrayList<>();
	/**
	 * total seats in the theater
	 */
	private int totalSeats;

	public static void main(String[] args) {
		ReserveSeats rs = new ReserveSeats();
		rs.compute();
	}

	private void compute() {
		try {
			System.out.println("enter input ... ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			int emptyLines = 0;
			while (emptyLines < 2) {
				String line = reader.readLine();
				if (line == null || "".equals(line.trim())) {
					System.out.println("--------------------------------------------------");
					emptyLines++;
				} else if (emptyLines == 0) {
					// read layout
					readLayout(line);
				} else {
					// read tickets request
					readTicketsRequest(line);
				}
			}
			reader.close();
			System.out.println(" Input received... ready to process seat requests... ");
			assignSeats();
		} catch (IOException e) {
			System.out.println("Unable to read the input... ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("\n"+e.getMessage());
		}
	}

	private void readLayout(String line) throws Exception {
		String[] array = line.split(" +");
		int layoutMapSize = layoutMap.size() + 1;
		for (String section : array) {
			if(section.chars().allMatch(Character::isDigit)){
				totalSeats += Integer.parseInt(section);
				layoutMap.computeIfAbsent(layoutMapSize, layoutList -> new ArrayList<>()).add(Integer.parseInt(section));	
			}else{
				throw new Exception("Invalid Layout");
			}
		}
	}

	private void readTicketsRequest(String line) throws Exception {
		String[] array = line.split(" +");
		if (array.length != 2 || !array[1].chars().allMatch(Character::isDigit)) {
			// invalid request
			throw new Exception("Invalid ticket request ");
		}
		requests.add(line);
	}

	private void assignSeats() {
		if (layoutMap.isEmpty() || requests.isEmpty()) {
			// no action
			return;
		}
		/**
		 * for each request, go thru the seat map and fill the possible requests
		 * from the first row and reduce the total number of available seats
		 */
		requests.forEach(s -> {
			String[] reqArr = s.split(" +");
			if (Integer.parseInt(reqArr[1]) > totalSeats) {
				System.out.println(reqArr[0] + " Sorry, we can't handle your party.");
			} else {
				fill(reqArr[0], Integer.parseInt(reqArr[1]));
			}
		});
	}

	private void fill(String name, int requestedSeats) {
		Integer remainingSeats = 0;
		for (Entry<Integer, List<Integer>> entry : layoutMap.entrySet()) {

			for (int i = 0; i < entry.getValue().size(); i++) {

				if (entry.getValue().get(i) >= requestedSeats) {

					System.out.println(name + " Row " + entry.getKey() + " Section " + (i + 1));

					remainingSeats = entry.getValue().get(i) - requestedSeats;
					totalSeats -= requestedSeats;

					entry.getValue().set(i, remainingSeats);

					return;
				}

			}
		}
		System.out.println(name + " Call to split party.");
	}
}
