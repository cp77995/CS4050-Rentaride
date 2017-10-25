package edu.uga.cs.rentaride.entity.impl;
import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.persistence.impl.PersistableImpl;

public class CommentImpl implements Comment {

	private String text;
	private Date date;
	private long id;
	private Customer customer;
	private Rental rental;

	public CommentImpl() {
		super();
		this.text = null;
		this.date = null;
		this.id = -1;
		//this.customer = null;
		this.rental = null;
	}
	
	public CommentImpl(String text,
					   Date date,
					   Rental rental)
					  // Customer customer) 
	{
		super();
		this.text = text;
		this.date = date;
		//this.id = id;
		//this.customer = customer;
		this.rental = rental;
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
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Rental getRental() {
		return rental;
	}

	@Override
	public void setRental(Rental rental) throws RARException {
		this.rental = rental;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

}
