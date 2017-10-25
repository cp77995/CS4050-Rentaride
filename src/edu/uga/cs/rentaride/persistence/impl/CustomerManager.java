package edu.uga.cs.rentaride.persistence.impl;

import java.util.List;

import com.mysql.jdbc.Connection;

import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class CustomerManager {

	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public CustomerManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
	
    public void save(Customer customer) {
		return;
	}
	
	public List<Customer> restore(Customer customer) {
		return null;
	}
	
	public void delete(Customer customer) {
		return;
	}

	
	
}
