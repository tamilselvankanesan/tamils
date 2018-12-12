package com.success.models;

import java.util.ArrayList;
import java.util.List;

public class SomeDocumentModels {
private List<SomeDocumentModel> models;
private List<String> versions;

public List<SomeDocumentModel> getModels() {
  return models;
}

public void setModels(List<SomeDocumentModel> models) {
  this.models = models;
}

public List<String> getVersions() {
  if(versions == null){
    versions = new ArrayList<>();
  }
  return versions;
}

public void setVersions(List<String> versions) {
  this.versions = versions;
}
}
