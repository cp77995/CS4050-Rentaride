package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentARideParams;

public class RentARideParamsImpl implements RentARideParams{

	private long 	id;
	private int 	membershipPrice;
	private int 	lateFee;
	
	public RentARideParamsImpl() {
		super();
		id = -1;
		membershipPrice = -1;
		lateFee = -1;
	}
	
	public RentARideParamsImpl(long id,
							   int membershipPrice,
							   int lateFee) 
	{
		super();
		this.id = id;
		this.membershipPrice = membershipPrice;
		this.lateFee = lateFee;
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
	public int getMembershipPrice() {
		return membershipPrice;
	}

	@Override
	public void setMembershipPrice(int membershipPrice) throws RARException {
		this.membershipPrice = membershipPrice;
	}

	@Override
	public int getLateFee() {
		return lateFee;
	}

	@Override
	public void setLateFee(int lateFee) throws RARException {
		this.lateFee = lateFee;
	}

}
