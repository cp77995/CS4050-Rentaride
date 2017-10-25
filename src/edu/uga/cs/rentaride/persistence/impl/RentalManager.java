package edu.uga.cs.rentaride.persistence.impl;

import java.sql.SQLException;
import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.impl.VehicleImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImpl;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;


public class RentalManager {
	
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public RentalManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    //Save implementation
    public void save(Rental rental) throws RARException {
    
        String              insertRentalSQL = "insert into rental (pickup_time, return_time, vehicle) values (?, ?, ?)";
        String              updateRentalSQL = "update rental set id = ?, customer = ?, pickup_time = ?, return_time = ?, vehicle = ?";
        PreparedStatement   stmt = null;
        int                 inscnt;
        long                id;
        
        try{
            //Persistence check
            if(!rental.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertRentalSQL );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateRentalSQL );
            
            //Set pickup time
            if(rental.getPickupTime() != null)
                stmt.setDate(1, (java.sql.Date) rental.getPickupTime());
            else
                throw new RARException("RentalManager.save: can't save a Rental: Pickup time is undefined");
            //Set return time
            if(rental.getReturnTime() != null)
                stmt.setDate(2, (java.sql.Date) rental.getReturnTime());
            else
                throw new RARException("RentalManager.save: can't save a Rental: Return time is undefined");
            //Set vehicle
            if(rental.getVehicle() != null)
                stmt.setInt(3, (int) rental.getVehicle().getId());
            else
                throw new RARException("RentalManager.save: can't save a Rental: Vehicle is undefined");
            
            //Update 
            if(rental.isPersistent())
                stmt.setLong( 6, rental.getId());
            
            inscnt = stmt.executeUpdate();
            
            if(!rental.isPersistent()){
                if(inscnt >=1){
                    String sql = "select last_insert_id()";
                    if(stmt.execute(sql)){
                        ResultSet r = stmt.getResultSet();
                        
                        while(r.next()){
                            id = r.getLong(1);
                            if(id != -1)
                                rental.setId(id);
                        }
                    }
                }
                else
                    throw new RARException("RentalManager.save: failed to save a Rental");
            }
            else{
                if(inscnt <1)
                    throw new RARException("RentalManager.save: failed to save a Rental");
            }
        }
        
        catch (SQLException e){
            throw new RARException("RentalManager.save: failed to save a Rental:" + e );
        }
        
    }
    
    public List<Rental> restore(Rental modelRental) throws RARException {
        String       selectASQL = "SELECT r.id, r.customer_id, r.vehicle_id, r.pickup_time, r.return_time, " + " from rental r WHERE";
        Statement 	 stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);
    	
        condition.setLength(0);
        
        query.append(selectASQL);
        
        if (modelRental != null) {
		    if(modelRental.getId() >= 0) {
		        query.append(" AND id = " + modelRental.getId());
		    } else {
		        if(modelRental.getId()!=-1)
		            condition.append(" AND id = '" + modelRental.getId() + "'");
		        if(modelRental.getCustomer()!=null)
		            condition.append(" AND customer = '" + modelRental.getCustomer() + "'");
		        if(modelRental.getPickupTime()!=null)
		            condition.append(" AND pickup_time = '" + modelRental.getPickupTime() + "'");
		        if(modelRental.getReturnTime()!=null)
		            condition.append(" AND return_time = '" + modelRental.getReturnTime() + "'");
		        if(modelRental.getVehicle()!=null)
		            condition.append(" AND vehicle = '" + modelRental.getVehicle() + "'");
		    } //else
        }//if
        
        try {
            
            stmt = conn.createStatement();
            
            if(stmt.execute(query.toString())){
                long    id;
                long  	customer_id;
                Date    pickup_time;
                Date    return_time;
                Vehicle vehicle;
                
                Rental rentalProxy = null;
                
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                    id = rs.getLong(1);
                    customer_id = rs.getLong(2);
                    pickup_time = rs.getDate(3);
                    return_time = rs.getDate(4);
                    //vehicle = rs.getString(5);
                    
                    rentalProxy = objectLayer.createRental();
                    rentalProxy.setId(id);
                    rentalProxy.setPickupTime(pickup_time);
                    rentalProxy.setReturnTime(return_time);
                }
            }
        }
        
        catch(SQLException e){
            throw new RARException("RentalManager.restore: COuld not restore persistent objects: Root cause: " + e);
        }
    throw new RARException("RentalManger.restore: Could not restore persistent Rental object");   
    }//Restore
    
    public void delete(Rental rental) throws RARException {
    	String               deleteCommentSQL = "delete from rental where id = ?";          
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!rental.isPersistent()) return;
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, rental.getId() );
            inscnt = stmt.executeUpdate();
            
            if (inscnt == 1)
            	return;
            else 
                throw new RARException( "RentalManager.delete: failed to delete this Rental" );
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "RentalManager.delete: failed to delete this Rental: " + e.getMessage() );
        }//catch
    }

	public void delete(Vehicle vehicle, Rental rental) {
		// TODO Auto-generated method stub
		
	}
}
