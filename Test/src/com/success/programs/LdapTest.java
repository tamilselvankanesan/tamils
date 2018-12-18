package com.success.programs;

import java.util.ArrayList;
import java.util.List;
import com.restfb.util.StringUtils;

public class LdapTest {

  public static void main(String[] args) {

    LdapTest ldapTest = new LdapTest();
    String members = "CN=LC6_PMs,OU=Groups,OU=WB,OU=O365 Sync,OU=_ENTERPRISE,DC=wb,DC=ad,DC=worldbank,DC=org, "
        + "CN=RM Peru,OU=Groups,OU=WB,OU=O365 Sync,OU=_ENTERPRISE,DC=wb,DC=ad,DC=worldbank,DC=org, CN=LC6PE Task Managers,OU=Security,"
        + "OU=WB,OU=O365 Sync,OU=_ENTERPRISE,DC=wb,DC=ad,DC=worldbank,DC=org, CN=LC6DMT,OU=Groups,OU=WB,OU=O365 Sync,"
        + "OU=_ENTERPRISE,DC=wb,DC=ad,DC=worldbank,DC=org, CN=LC6 Operations Team,OU=Groups,OU=WB,OU=O365 Sync,"
        + "OU=_ENTERPRISE,DC=wb,DC=ad,DC=worldbank,DC=org, CN=000231151,OU=GGO-DC,OU=GGO,OU=Users-Groups-Workstations,OU=_WB,DC=wb,DC=ad,DC=worldbank,DC=org,CN=000231151,OU=Contacts,OU=GGO,OU=Users-Groups-Workstations,OU=_WB,DC=wb,DC=ad,DC=worldbank,DC=org,CN=000386037,OU=LCR-CO-BR,OU=LCR-CO,OU=LCR,OU=Users-Groups-Workstations,OU=_WB,DC=wb,DC=ad,DC=worldbank,DC=org, CN=000233859,OU=LCR-CO-AR,OU=LCR-CO,OU=LCR,OU=Users-Groups-Workstations,OU=_WB,DC=wb,DC=ad,DC=worldbank,DC=org, CN=000259518,OU=ECA-CO-HR,OU=ECA-CO,OU=ECA,OU=Users-Groups-Workstations,OU=_WB,DC=wb,DC=ad,DC=worldbank,DC=org, CN=000241112,OU=TRE-DC,OU=TRE,OU=Users-Groups-Workstations,OU=_WB,DC=wb,DC=ad,DC=worldbank,DC=org, "
        + "CN=000205386,OU=LCR-CO-BR,OU=LCR-CO,OU=LCR,OU=Users-Groups-Workstations,OU=_WB,DC=wb,DC=ad,DC=worldbank,DC=org";
    getCn(members);
  }

  private static String getCn(String members) {
    String[] member = members.split("CN=");
    List<String> result = new ArrayList<>();
    for(String m: member){
      if(!"".equals(m) && !m.contains("OU=Contacts") && !m.contains("OU=Security")){
        result.add(m.substring(0, m.indexOf(",")));
      }
    }
    System.out.println(StringUtils.join(result));
    return StringUtils.join(result);
  }
}
