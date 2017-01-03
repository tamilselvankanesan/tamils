package com.cmecf.daos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cmecf.model.Packet;

interface PacketRepository extends CrudRepository<Packet, Integer>{
	List<Packet> findByCaseId(Integer caseId);
}
