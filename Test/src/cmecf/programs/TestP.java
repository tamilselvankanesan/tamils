package cmecf.programs;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


public class TestP {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
	  System.out.println("hello world");
	  
	  Set<String> ss = new HashSet<>();
	  StringUtils.join(ss, ",");
	  System.out.println(ss);
	  
	  Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, 7200000);
		System.out.println(new Date(calendar.getTime().getTime()));
		
		Instant instant = new Date().toInstant();
		System.out.println(instant);
		
		
		System.out.println(Timestamp.from(new Date().toInstant()));
		
		ZonedDateTime local = ZonedDateTime.now();
		System.out.println("local "+local);
		ZonedDateTime gmt = local.withZoneSameInstant(ZoneId.of("GMT"));
		System.out.println("gmt "+gmt);
		Timestamp ts = Timestamp.valueOf(gmt.toLocalDateTime());
		System.out.println("ts "+ts);
  }
}
