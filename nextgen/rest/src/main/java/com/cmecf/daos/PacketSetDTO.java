package com.cmecf.daos;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.cmecf.model.Packet;
@XmlRootElement
public class PacketSetDTO {

	private List<Packet> packets;

	public List<Packet> getPackets() {
		return packets;
	}

	public void setPackets(List<Packet> packets) {
		this.packets = packets;
	}
}
