package com.cmecf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmecf.daos.PacketSetDTO;
import com.cmecf.facades.PacketFacade;
import com.cmecf.model.Packet;

@RestController
@RequestMapping(value="/packet")
public class PacketService {
	
	@Autowired
	private PacketFacade facade;
	
	@RequestMapping(path="/getPacketsSet", method=RequestMethod.GET, produces={"application/json"})
	public PacketSetDTO getPacketsSet(){
		return facade.getPackets();
	}
	@RequestMapping(path="/getPackets", method=RequestMethod.GET, produces={"application/json"})
	public List<Packet> getPackets(){
		return facade.getPackets().getPackets();
	}
}
