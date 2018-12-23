package com.success.programs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JRPRuleCopySetDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  @XmlElement(name="jrpRuleCopyDTO")
  private List<JRPRuleCopyDTO> jrpRuleCopyDTO = new ArrayList<>();
  public List<JRPRuleCopyDTO> getData() {
    return jrpRuleCopyDTO;
  }
}
