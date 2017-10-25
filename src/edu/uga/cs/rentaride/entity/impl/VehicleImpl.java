package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;

public class VehicleImpl implements Vehicle {

	//Vehicle attributes
	private String make;
	private String model;
	private int year;
	private int mileage;
	private String tag;
	private Date lastServiced;
	private long id;
	
	//References
	private VehicleType vehicleType;
	private VehicleStatus vehicleStatus;
	private VehicleCondition vehicleCondition;
	private RentalLocation rentalLocation;
	private List<Rental> rentals;
	
	public VehicleImpl() {
		super();
		make = null;
		model = null;
		year = -1;
		mileage = -1;
		tag = null;
		lastServiced = null;
		id = -1;
		
		vehicleType = null;
		vehicleStatus = null;
		vehicleCondition = null;
		rentalLocation = null;
		rentals = null;
	}
	
	public VehicleImpl(String make,
					   String model,
					   int year,
					   String tag,
					   int mileage,
					   Date lastServiced,
					   //long id,
					   VehicleType vehicleType,
					   RentalLocation rentalLocation,
					   VehicleCondition vehicleCondition,
					   VehicleStatus vehicleStatus
					   //List<Rental> rentals
					  ) 
	{
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.tag = tag;
		this.mileage = mileage;
		this.lastServiced = lastServiced;
		//this.id = id;
		
		this.vehicleType = vehicleType;
		this.vehicleStatus = vehicleStatus;
		this.vehicleCondition = vehicleCondition;
		this.rentalLocation = rentalLocation;
		this.rentals = rentals;
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
	public String getMake() {
		return make;
	}

	@Override
	public void setMake(String make) {
		this.make = make;
	}

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public void setYear(int year) throws RARException {
		this.year = year;
	}

	@Override
	public String getRegistrationTag() {
		return tag;
	}

	@Override
	public void setRegistrationTag(String registrationTag) {
		this.tag = registrationTag;
	}

	@Override
	public int getMileage() {
		return mileage;
	}

	@Override
	public void setMileage(int mileage) throws RARException {
		this.mileage = mileage;
	}

	@Override
	public Date getLastServiced() {
		return lastServiced;
	}

	@Override
	public void setLastServiced(Date lastServiced) {
		this.lastServiced = lastServiced;
	}

	@Override
	public VehicleStatus getStatus() {
		return vehicleStatus;
	}

	@Override
	public void setStatus(VehicleStatus status) {
		this.vehicleStatus = status;
	}

	@Override
	public VehicleCondition getCondition() {
		return vehicleCondition;
	}

	@Override
	public void setCondition(VehicleCondition condition) {
		this.vehicleCondition = condition;
	}

	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public RentalLocation getRentalLocation() {
		return rentalLocation;
	}

	@Override
	public void setRentalLocation(RentalLocation rentalLocation) throws RARException {
		this.rentalLocation = rentalLocation;
	}

	@Override
	public List<Rental> getRentals() {
		return rentals;
	}

}
