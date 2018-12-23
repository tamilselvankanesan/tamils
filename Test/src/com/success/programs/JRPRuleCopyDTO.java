package com.success.programs;

import java.io.Serializable;
import java.util.Map;
public class JRPRuleCopyDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private Integer rfstId;
  private Integer judgePrid;
  private Map<String, String> jrpDefaults;
  
  public Integer getRfstId() {
    return rfstId;
  }
  public void setRfstId(Integer rfstId) {
    this.rfstId = rfstId;
  }
  public Integer getJudgePrid() {
    return judgePrid;
  }
  public void setJudgePrid(Integer judgePrid) {
    this.judgePrid = judgePrid;
  }
  public Map<String, String> getJrpDefaults() {
    return jrpDefaults;
  }
  public void setJrpDefaults(Map<String, String> jrpDefaults) {
    this.jrpDefaults = jrpDefaults;
  }
  
}
