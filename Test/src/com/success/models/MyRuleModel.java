package com.success.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MyRuleModel {

  @XmlElement(name="hello")
//  @XmlAttribute(name="test")
  protected Map<Integer, SomeDocumentModels> someDocumentsMap;
  @XmlElement
  private List<String> ids;
  
  @XmlElement
  private Map<Integer, ArrayList<String>> myDocumentModelsMap;
  
  @XmlElement
  private Map<Integer, ArrayList<SomeDocumentModel>> mySomeDocumentModelsMap;
  
  @XmlElement
  private Map<String, HashSet<String>> actualMap;
  
  @XmlElement(name="documents")
  private ArrayList<String> dVersions = new ArrayList<>();

  public Map<Integer, SomeDocumentModels> getSomeDocumentsMap() {
    return someDocumentsMap;
  }

  public void setSomeDocumentsMap(Map<Integer, SomeDocumentModels> someDocumentsMap) {
    this.someDocumentsMap = someDocumentsMap;
  }

  public List<String> getIds() {
    return ids;
  }

  public void setIds(List<String> ids) {
    this.ids = ids;
  }

  public Map<Integer, ArrayList<String>> getMyDocumentModelsMap() {
    return myDocumentModelsMap;
  }

  public void setMyDocumentModelsMap(Map<Integer, ArrayList<String>> myDocumentModelsMap) {
    this.myDocumentModelsMap = myDocumentModelsMap;
  }

  public Map<Integer, ArrayList<SomeDocumentModel>> getMySomeDocumentModelsMap() {
    return mySomeDocumentModelsMap;
  }

  public void setMySomeDocumentModelsMap(Map<Integer, ArrayList<SomeDocumentModel>> mySomeDocumentModelsMap) {
    this.mySomeDocumentModelsMap = mySomeDocumentModelsMap;
  }

  public Map<String, HashSet<String>> getActualMap() {
    return actualMap;
  }

  public void setActualMap(Map<String, HashSet<String>> actualMap) {
    this.actualMap = actualMap;
  }

  public ArrayList<String> getdVersions() {
    return dVersions;
  }

  public void setdVersions(ArrayList<String> dVersions) {
    this.dVersions = dVersions;
  }
}
