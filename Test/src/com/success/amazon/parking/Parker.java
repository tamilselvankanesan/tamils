package com.success.amazon.parking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parker {
	
	private List<ParkingSpace> availableSpace=null;
	private List<ParkingSpace> occuipedSpace=null;
	private boolean full;
	private static Parker parker = null;
	private Parker(){
		availableSpace = new ArrayList<>();
		occuipedSpace = new ArrayList<>();
		int count = 1;
		for(int i=0; i<20; i++){
			ParkingSpace ps = new ParkingSpace(ParkingType.REGULAR);
			ps.setLot(count++);
			availableSpace.add(ps);
		}
		for(int i=0; i<5; i++){
			ParkingSpace ps = new ParkingSpace(ParkingType.HANDICAPPED);
			ps.setLot(count++);
			availableSpace.add(ps);
		}
	}
	
	public static Parker getInstance(){
		if(parker == null){
			synchronized (Parker.class) {
				if(parker == null){
					parker = new Parker();
				}
			}
		}
		return parker;
	}
	private ParkingSpace findAvilableParkingSpace(ParkingType parkingType){
		Iterator<ParkingSpace> psItr = availableSpace.iterator();
		while(psItr.hasNext()){
			ParkingSpace ps = psItr.next();
			if(ps.getParkingType().equals(parkingType)){
				return ps;
			}
		}
		return null;
	}
	
	public void park(ParkingType parkingType, Vehicle vehicle){
		if(!isFull()){
			
			ParkingSpace ps = findAvilableParkingSpace(parkingType);
			if(ps!=null){
				ps.setVehicle(vehicle);
				occuipedSpace.add(ps);
				availableSpace.remove(ps);
				
				if(availableSpace.isEmpty()){
					full = true;
				}
			}
		}
		
	}
	public void unPark(Vehicle vehicle){
		
		if(!occuipedSpace.isEmpty()){
			Iterator<ParkingSpace> psItr = occuipedSpace.iterator();
			while(psItr.hasNext()){
				ParkingSpace ps = psItr.next();
				if(ps.getVehicle().equals(vehicle)){
					availableSpace.add(ps);
					occuipedSpace.remove(ps);
					full = false;
					break;
				}
			}
		}
	}

	/**
	 * @return the full
	 */
	public boolean isFull() {
		return full;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
