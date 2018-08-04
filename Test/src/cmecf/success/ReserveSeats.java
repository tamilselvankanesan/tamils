package cmecf.success;

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

	private void overview(){
		System.out.println("#############################################  Application Overview  #############################################");
		System.out.println("The theater layout is made up of 1 or more rows.  Each row is made up of 1 or more sections separated by a space.");
		System.out.println("After the theater layout, there is one empty line, followed by 1 or more theater requests. ");
		System.out.println("The theater request is made up of a name followed by a space and the number of requested tickets.");
		System.out.println("##################################################################################################################");
	}
	private void compute() {
		try {
			overview();
			System.out.println("\nEnter seat layout  .... ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			int emptyLines = 0;
			while (emptyLines < 2) {
				String line = reader.readLine();
				if (line == null || "".equals(line.trim())) {
					emptyLines++;
				} else if (emptyLines == 0) {
					// read layout
					readLayout(line);
				} else {
					// read tickets request
					readTicketsRequest(line);
				}
				if(emptyLines == 1 && (line == null || "".equals(line.trim()))){
					System.out.println("Enter seat requests .... ");
				}
			}
			reader.close();
			assignSeats();
		} catch (IOException e) {
			System.out.println("\n Unable to read the input... ");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("\n"+e.getMessage());
		}
	}

	private void readLayout(String line) throws Exception {
		String[] array = line.split(" +");
		int rowNum = layoutMap.size() + 1;
		for (String section : array) {
			if(isNumber(section)){
				totalSeats += Integer.parseInt(section);
				addToLayoutMap(rowNum, Integer.parseInt(section));
			}else{
				throw new Exception("Invalid Layout");
			}
		}
	}
	
	private void addToLayoutMap(Integer rowNum, Integer section){
		if(!layoutMap.containsKey(rowNum)){
			layoutMap.put(rowNum, new ArrayList<>());
		}
		layoutMap.get(rowNum).add(section);
	}
	
	private boolean isNumber(String ip){
		try {
			Integer.parseInt(ip);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private void readTicketsRequest(String line) throws Exception {
		String[] array = line.split(" +");
		if (array.length != 2 || !isNumber(array[1])) {
			// invalid request
			throw new Exception("Invalid ticket request ");
		}
		requests.add(line);
	}

	private void assignSeats() {
		if (layoutMap.isEmpty() || requests.isEmpty()) {
			// no action
			System.out.println("This request cannot be processed. Seat layout and Ticket request is required to process this request.");
			return;
		}
		System.out.println("Input received... Ready to process seat requests... \n");
		System.out.println("Here are the seat allocations ... \n ");
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
