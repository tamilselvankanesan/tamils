package com.cmecf.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Packet {

	private String packetName;
	private Integer docNum;
	private String event;
	private String createdBy;
	private Date createdDate;
	
	public String getPacketName() {
		return packetName;
	}
	public void setPacketName(String packetName) {
		this.packetName = packetName;
	}
	public Integer getDocNum() {
		return docNum;
	}
	public void setDocNum(Integer docNum) {
		this.docNum = docNum;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
