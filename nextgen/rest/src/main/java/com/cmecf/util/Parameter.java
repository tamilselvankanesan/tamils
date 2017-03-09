package com.cmecf.util;

import org.springframework.util.MultiValueMap;

public class Parameter {

	private String entryId;
	private String caseId;
	private String ripeMotionId;
	private MultiValueMap<String, String> params;
	
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getRipeMotionId() {
		return ripeMotionId;
	}
	public void setRipeMotionId(String ripeMotionId) {
		this.ripeMotionId = ripeMotionId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public MultiValueMap<String, String> getParams() {
		return params;
	}
	public void setParams(MultiValueMap<String, String> params) {
		this.params = params;
	}
}
