package edu.uga.cs.rentaride.persistence.impl;

import java.util.List;

import com.mysql.jdbc.Connection;

import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class ReservationManager {
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public ReservationManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }

    public void save(Reservation reservation) {
		
	}
    
    public void save(Customer customer, Reservation reservation) {
		//???
	}
    
	public List<Reservation> restore(Reservation modelReservation) {
		return null;
	}
	
	//???
	public Customer restoreCustomerReservation(Reservation reservation) {
		return null;
	}
	
	//???
	public List<Reservation> restoreCustomerReservation(Customer customer) {
		return null;
	}
	
	public void delete(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	public void deleteCustomerReservation(Customer customer, Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	
    
}
