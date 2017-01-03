package com.cmecf.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jrpacket {
	@Id
	@Column
	private Integer packetId;
	public Integer getPacketId() {
		return packetId;
	}
	public void setPacketId(Integer packetId) {
		this.packetId = packetId;
	}
	public String getPacketName() {
		return packetName;
	}
	public void setPacketName(String packetName) {
		this.packetName = packetName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	@Column
	private String packetName;
	@Column
	private Date createdDate;
	@Column
	private String createdBy;
	@Column
	private Integer caseId;	
}
