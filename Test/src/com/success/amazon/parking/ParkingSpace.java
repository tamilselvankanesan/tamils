package com.success.amazon.parking;

public class ParkingSpace {
	private int lot;
	private Vehicle vehicle;
	private ParkingType parkingType;
	public ParkingSpace(ParkingType parkingType){
		this.parkingType = parkingType;
	}
	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	/**
	 * @return the parkingType
	 */
	public ParkingType getParkingType() {
		return parkingType;
	}
	/**
	 * @param parkingType the parkingType to set
	 */
	public void setParkingType(ParkingType parkingType) {
		this.parkingType = parkingType;
	}
	/**
	 * @return the lot
	 */
	public int getLot() {
		return lot;
	}
	/**
	 * @param lot the lot to set
	 */
	public void setLot(int lot) {
		this.lot = lot;
	}

}
