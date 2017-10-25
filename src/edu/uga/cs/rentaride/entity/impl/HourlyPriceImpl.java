package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;

public class HourlyPriceImpl implements HourlyPrice {

	private int 		maxHours;
	private int 		price;
	private long   		id;
	private VehicleType vehicleType;
	
	public HourlyPriceImpl() {
		super();
		this.maxHours = -1;
		this.price = -1;
		this.id = -1;
		this.vehicleType = null;
	}
	
	public HourlyPriceImpl(int maxHours,
						   int price,
						   VehicleType vehicleType) 
	{
		super();
		this.maxHours = maxHours;
		this.price = price;
		this.vehicleType = vehicleType;
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
	public int getMaxHours() {
		return maxHours;
	}

	@Override
	public void setMaxHours(int maxHours) throws RARException {
		this.maxHours = maxHours;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) throws RARException {
		this.price = price;
	}

	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException {
		this.vehicleType = vehicleType;
	}

}
