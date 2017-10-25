package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;

public class RentalLocationImpl implements RentalLocation {

	private String 	name;
	private String 	address;
	private int 	capacity;
	private long 	id;
	
	public RentalLocationImpl() {
		super();
		name = null;
		address = null;
		capacity = -1;
		id = -1;
	}
	
	public RentalLocationImpl(String name,
							  String address,
							  int capacity)
							  //long id) 
	{
		super();
		this.name = name;
		this.address = address;
		this.capacity = capacity;
		//this.id = id;
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
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public void setCapacity(int capacity) throws RARException {
		this.capacity = capacity;
	}

}
