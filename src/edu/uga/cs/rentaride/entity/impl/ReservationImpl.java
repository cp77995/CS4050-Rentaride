package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.VehicleType;

public class ReservationImpl implements Reservation {

	private Date 			pickup;
	private int 			length;
	private boolean 		isCancelled;
	private long 			id;
	
	private Customer 		customer;
	private VehicleType 	vehicleType;
	private Rental 			rental;
	private RentalLocation 	rentalLocation;
	
	
	public ReservationImpl() {
		super();
		pickup = null;
		length = -1;
		isCancelled = false;
		id = -1;
		
		customer = null;
		vehicleType = null;
		rental = null;
		rentalLocation = null;
	}
	
	public ReservationImpl(Date pickup, 
						   int length, 
						   //boolean isCancelled,
						   //long id,
						   VehicleType vehicleType,
						   RentalLocation rentalLocation,
						   Customer customer) 
	{
		super();
		this.pickup = pickup;
		this.length = length;
		//this.isCancelled = isCancelled;
		//this.id = id;
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.rental = rental;
		this.rentalLocation = rentalLocation;
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
	public Date getPickupTime() {
		return pickup;
	}

	@Override
	public void setPickupTime(Date pickupTime) throws RARException {
		this.pickup = pickupTime;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public void setLength(int length) throws RARException {
		this.length = length;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(Customer customer) throws RARException {
		this.customer = customer;
	}

	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	@Override
	public void setVehicleType(VehicleType vehicleType) throws RARException {
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
	public Rental getRental() {
		return rental;
	}

	@Override
	public void setRental(Rental rental) {
		this.rental = rental;
	}

}
