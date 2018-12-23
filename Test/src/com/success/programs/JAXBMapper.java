package com.success.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import com.success.models.MyRuleModel;
import com.success.models.SomeDocumentModel;
import com.success.models.SomeDocumentModels;

public class JAXBMapper {

  public static void main(String[] args) {

    MyRuleModel myRulemodel = new MyRuleModel();
    List<String> someStrings = new ArrayList<>();
    someStrings.add("string 1");
    someStrings.add("string 2");
    someStrings.add("string 3");
    Map<Integer, SomeDocumentModels> test = new HashMap<>();
//    List<SomeDocumentModel> models = new ArrayList<>();
//    models.add(new SomeDocumentModel("version 1"));
//    models.add(new SomeDocumentModel("version 2"));
    
    SomeDocumentModels smodels = new SomeDocumentModels();
//    smodels.setModels(models);
    smodels.getVersions().add("v1");
    smodels.getVersions().add("v2");
    test.put(1, smodels);
    
    smodels = new SomeDocumentModels();
//    models = new ArrayList<>();
    
//    models.add(new SomeDocumentModel("version 3"));
//    models.add(new SomeDocumentModel("version 4"));
//    smodels.setModels(models);
    smodels.getVersions().add("v3");
    smodels.getVersions().add("v4");
    test.put(2, smodels);
    
//    myRulemodel.setSomeDocumentsMap(test);
//    myRulemodel.setIds(someStrings);
    
    
    Map<Integer, ArrayList<String>> myDocumentModelsMap = new HashMap<>();
    ArrayList<String> docuementStrs = new ArrayList<>();
    docuementStrs.add("my doc 1");docuementStrs.add("my doc 2");
    myDocumentModelsMap.put(1, docuementStrs);
    
    docuementStrs = new ArrayList<>();
    docuementStrs.add("my doc 4");docuementStrs.add("my doc 5");
    myDocumentModelsMap.put(2, docuementStrs);
//    myRulemodel.setMyDocumentModelsMap(myDocumentModelsMap);
    
    Map<Integer, ArrayList<SomeDocumentModel>> mySomeDocumentModelsMap = new HashMap<>();
    ArrayList<SomeDocumentModel> aList = new ArrayList<>();
    aList.add(new SomeDocumentModel("some document version 1"));
    aList.add(new SomeDocumentModel("some document version 2"));
    aList.add(new SomeDocumentModel("some document version 3"));
    mySomeDocumentModelsMap.put(1, aList);
    
    aList = new ArrayList<>();
    aList.add(new SomeDocumentModel("some document version 4"));
    aList.add(new SomeDocumentModel("some document version 5"));
    aList.add(new SomeDocumentModel("some document version 6"));
    mySomeDocumentModelsMap.put(2, aList);
//    myRulemodel.setMySomeDocumentModelsMap(mySomeDocumentModelsMap);
    
    
    Map<String, HashSet<String>> actualMap = new LinkedHashMap<>();
    HashSet<String> versions = new HashSet<>();
    versions.add("v1");versions.add("v2");
    actualMap.put("1", versions);
//    myRulemodel.setActualMap(actualMap);
    
    ArrayList<String> dVersions = new ArrayList<>();
    dVersions.add("vvvvv1");dVersions.add("vvvvv2");
    myRulemodel.setdVersions(dVersions);
    
    try {
      JAXBContext ctx = JAXBContext.newInstance(MyRuleModel.class);
      Marshaller marshal = ctx.createMarshaller();
      marshal.marshal(myRulemodel, System.out);
      
    }
    catch (JAXBException e) {
      e.printStackTrace();
    }
    
  }

}
