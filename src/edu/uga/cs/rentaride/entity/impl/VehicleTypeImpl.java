package edu.uga.cs.rentaride.entity.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;

public class VehicleTypeImpl implements VehicleType {

	private String name;
	private long id;
	
	private List<HourlyPrice> 	 hourlyPrices;
	private List<Vehicle> 		 vehicles;
	private List<Reservation>	 reservations;
	
	public VehicleTypeImpl() {
		super();
		
		name = null;
		id = -1;
		hourlyPrices = null;
		vehicles = null;
		reservations = null;
	}
	
	public VehicleTypeImpl(String name)
						   //long id,
						   //List<HourlyPrice> hourlyPrices,
						   //List<Vehicle> vehicles,
						   //List<Reservation> reservations) 
	{
		super();
		
		name = null;
		//id = -1;
		//hourlyPrices = null;
		//vehicles = null;
		//reservations = null;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean isPersistent() {
		if (id != -1) return true;
		else return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) throws RARException {
		this.name = name;
	}

	@Override
	public List<HourlyPrice> getHourlyPrices() {
		return hourlyPrices;
	}

	@Override
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	@Override
	public List<Reservation> getReservations() {
		return reservations;
	}
	
}
