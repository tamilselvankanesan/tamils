package com.cmecf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public PacketSetDTO getPacketsSet(@RequestParam(name="input") String input){
		System.out.println("Entered search param :"+input);
		return facade.getPackets();
	}
	@RequestMapping(path="/getPackets", method=RequestMethod.GET, produces={"application/json"})
	public List<Packet> getPackets(){
		return facade.getPackets().getPackets();
	}
	@RequestMapping(path="/getPacketsByJson", method=RequestMethod.POST, produces={"application/json"}, consumes={"application/json"})
	public @ResponseBody List<Packet> getPackets(@RequestBody Packet packet){
		System.out.println("Search using Packet Object");
		System.out.println(packet.getPacketName());
		return facade.getPackets().getPackets();
	}
}
