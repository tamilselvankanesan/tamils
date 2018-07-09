package com.success;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
  private static void remove(Integer jrpId){
    if(jrpId!=null && jrpId>0){
      System.out.println("> 0");
    }
    List<String> aa = new ArrayList<>();
    
        aa.add("1");aa.add("12");aa.add("11");aa.add("11");
        long count = aa.stream().filter(s -> s.equals("111")).count();
        System.out.println("Count -"+count);
        for(String ss : aa){
          if(ss.equals("12")){
            aa.remove(2);
            System.out.println("Remoced");
//            break;
          }
        }
  }
	public static void main(String[] args) {
	  String s = "9:07-cr-00001-1";
	  int secondIndex = s.indexOf('-', s.indexOf('-')+1);
	  if(secondIndex!=-1){
	    System.out.println(s.substring(0, secondIndex));
	  }
	  String mainChapterNumber = s.split("\\-", 3)[0];
	  System.out.println(mainChapterNumber);
	  
	  
	  List<String> ll = new ArrayList<>();
//	  ll.add("ddd");ll.add("111ddd");
	  System.out.println(String.join(",", ll).split(",").length);
	  
	  remove(1);
//		read();
		List<String> aa1 = new ArrayList<String>();
		aa1.add(null);
		aa1.add(null);
		Map<String, List<String>> map = new HashMap<>();
		map.put("10", aa1);
		Integer ss1 = 101;
		List<String> aa = new ArrayList<String>();
		List<String> bb = null;
//		aa.addAll(map.get(ss1.toString()));
				
		String ss = "NextGen CM/ECF Release 1.1) Revision 1.1.1";
		if(ss.indexOf("Revision")!=-1 && ss.lastIndexOf(")")!=-1 && ss.indexOf("Revision")+8 <ss.lastIndexOf(")")){
//			//System.out.println("hhh");
			String s1 = ss.substring(ss.indexOf("Revision")+8,ss.lastIndexOf(")"));
//			//System.out.println(s1);
		}
		
		Calendar cal=Calendar.getInstance();
		cal.add( Calendar.DAY_OF_WEEK, -(cal.get(Calendar.DAY_OF_WEEK)-1)); 
//		//System.out.println(cal.getTime());
		
//		String link = "https://ecf.cm5b.aocms.uscourts.gov/n/beam/servlet/12345";
		String link = "12345";
		int lastSlash = link.lastIndexOf("/");
		//System.out.println(lastSlash);
		//System.out.println(link.length());
		if(link.length() > lastSlash){
			String id = link.substring(lastSlash+1, link.length());
			//System.out.println(id);
			try {
				Long.parseLong(id);
			} catch (NumberFormatException e) {
				//System.out.println("error");
			}
		}
		
	}
	
	private static void read() {

		String fileName = "C:\\Tamil\\WinSCP_Docs\\cm1d\\bankruptcy_diffs.log";

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String line;
			while ((line = br.readLine()) != null) {
				
				if(line.contains("bankruptcy-1-1-2/ui/JudgesReviewPacket")){
					System.out.println(line);
				}
				if(line.contains("/opt/nextgen/bankruptcy-1-1-2/ui/configurationtreelib")){
					System.out.println(line);
				}
				if(line.contains("/opt/nextgen/bankruptcy-1.2/restclient")){
					System.out.println(line);
				}
				if(line.contains("/opt/nextgen/bankruptcy-1-1-2/ws/composite")){
					System.out.println(line);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
