package com.cmecf.facades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmecf.daos.PacketDAO;
import com.cmecf.daos.PacketSetDTO;
import com.cmecf.model.Packet;
@Component
public class PacketFacade {

	@Autowired
	private PacketDAO packetDao;
	
	public PacketSetDTO getPackets(){
		List<Packet> packets = packetDao.getPackets(); 
		PacketSetDTO set = new PacketSetDTO();
		set.setPackets(packets);
		return set;
	}
}
