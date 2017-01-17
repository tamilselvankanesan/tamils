package com.cmecf.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cmecf.model.Packet;
@Component
public class PacketDAO {
	
	public List<Packet> getPackets(){
		List<Packet> packets = new ArrayList<>();
		for(int i=1; i<11; i++){
			Packet p = new Packet();
			p.setPacketName("Test Packet "+i);
			p.setCreatedBy("Tamilselvan "+i);
			p.setCreatedDate(new Date());
			p.setDocNum(i);
			p.setEvent("event "+i);
			packets.add(p);
		}
		return packets;
	}
}
