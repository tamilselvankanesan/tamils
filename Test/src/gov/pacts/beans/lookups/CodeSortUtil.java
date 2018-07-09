package gov.pacts.beans.lookups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CodeSortUtil {

	private static Map<String, Integer> sortOrder = new HashMap<>();
	static{
		sortOrder.put("Report within 72 hours of release (or sentence)",1);
		sortOrder.put("Report to the probation officer",2);
		sortOrder.put("Not leave the judicial district without permission",3);
		sortOrder.put("Answer truthfully the inquiries by the probation officer",4);
		sortOrder.put("Approval of residence and notification of change in residence",5);
		sortOrder.put("Permit officer field visits and permit confiscation of contraband in plain view.",6);
		sortOrder.put("Work regularly at a lawful occupation and notification of change in employment",7);
		sortOrder.put("No criminal association",8);
		sortOrder.put("Notify of arrest or questioning by law enforcement officer",9);
		sortOrder.put("Do not possess firearm, ammunition, destructive device, or dangerous weapon",10);
		sortOrder.put("Do not act as informer without permission",11);
		sortOrder.put("Third party risk notification",12);
		sortOrder.put("Follow instructions of officer related to conditions",13);
	}
	
	public static List<Object> sort(List<Object> unOrderedList){
		SortedMap<Integer, Object> sortedMap = new TreeMap<>();
		for(Object obj : unOrderedList){
			CodeLookupBean clb = (CodeLookupBean)obj;
			sortedMap.put(sortOrder.get(clb.getDescription()), obj);	
		}
		List<Object> sortedList = new ArrayList<>(sortedMap.values());
		return sortedList;
	}
	
	public static void main(String[] args) {
		CodeLookupBean b1 = new CodeLookupBean("AV", "Answer truthfully the inquiries by the probation officer");
		CodeLookupBean b2 = new CodeLookupBean("AS", "Report within 72 hours of release (or sentence)");
		CodeLookupBean b3 = new CodeLookupBean("AK", "Work regularly at a lawful occupation and notification of change in employment");
		CodeLookupBean b4 = new CodeLookupBean("MK", "Report to the probation officer");
		CodeLookupBean b5 = new CodeLookupBean("MAA", "Follow instructions of officer related to conditions");
		CodeLookupBean b6 = new CodeLookupBean("MA", "Notify of arrest or questioning by law enforcement officer");
		
		List<Object> unOrderedList = new ArrayList<>();
		unOrderedList.add(b1);unOrderedList.add(b2);	
		unOrderedList.add(b3);unOrderedList.add(b4);
		unOrderedList.add(b5);unOrderedList.add(b6);
		sort(unOrderedList);
	}
}
