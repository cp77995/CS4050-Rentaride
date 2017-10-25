package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;

public class RentalImpl implements Rental {

	//Rental attributes
	private Date		pickupDate;
	private Date		returnDate;
	private boolean		isLate;
	private int			charges;
	
	private long 		id;
	
	//References
	private Customer    customer;
	private Comment     comment;
	private Reservation reservation;
	private Vehicle     vehicle;
	
	public RentalImpl() {
		super();
		pickupDate = null;
		returnDate = null;
		isLate = false;
		charges = -1;
		
		id = -1;
		
		customer = null;
		comment = null;
		reservation = null;
		vehicle = null;
	}
	
	public RentalImpl(Date pickupDate,
					  //Date returnDate,
					  //boolean isLate,
					  //int charges,
					  //long id,
					  //Customer customer,
					  //Comment comment,
					  Reservation reservation,
					  Vehicle vehicle)
	{
		super();
		this.pickupDate = pickupDate;
		//this.returnDate = returnDate;
		//this.isLate = isLate;
		//this.charges = charges;
		
		//this.customer = customer;
		//this.comment = comment;
		this.reservation = reservation;
		this.vehicle = vehicle;
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
		return pickupDate;
	}

	@Override
	public void setPickupTime(Date pickupTime) {
		this.pickupDate = pickupTime;
	}

	@Override
	public Date getReturnTime() {
		return returnDate;
	}

	@Override
	public void setReturnTime(Date returnTime) throws RARException {
		this.returnDate = returnTime;
	}

	@Override
	public boolean getLate() {
		return isLate;
	}

	@Override
	public int getCharges() {
		return charges;
	}

	@Override
	public void setCharges(int charges) throws RARException {
		this.charges = charges;
	}

	@Override
	public Reservation getReservation() {
		return reservation;
	}

	@Override
	public void setReservation(Reservation reservation) throws RARException {
		this.reservation = reservation;
	}

	@Override
	public Vehicle getVehicle() {
		return vehicle;
	}

	@Override
	public void setVehicle(Vehicle vehicle) throws RARException {
		this.vehicle = vehicle;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public Comment getComment() {
		return comment;
	}

	@Override
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
}