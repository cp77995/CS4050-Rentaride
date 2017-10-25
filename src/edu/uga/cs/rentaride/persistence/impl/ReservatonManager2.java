package edu.uga.cs.rentaride.persistence.impl;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.entity.impl.VehicleImpl;
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class ReservationManager {
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public ReservationManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    //Save implementation
    public void save(Reservation Reservation) throws RARException {
    
        String              insertReservationSql = "insert into reservation (pickup_time, rental_length) values ( ?, ?)";
        String              updateReservationSql = "update Reservation set pickup_time = ?, rental_length = ?";
        PreparedStatement   stmt = null;
        int                 inscnt;
        long                id;
        
        try{
            //Persistence check
            if(!Reservation.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertReservationSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateReservationSql );
           
            //Set pickup time
            if(Reservation.getPickupTime() != null)
                stmt.setDate(1, (java.sql.Date) Reservation.getPickupTime());
            else
                throw new RARException("ReservationManager.save: can't save a Reservation: Pickup time is undefined");
            //Set return time
            if(Reservation.getLength() >= -1)
                stmt.setInt(2, Reservation.getLength());
            else
                throw new RARException("ReservationManager.save: can't save a Reservation: Return time is undefined");
            
            //Update Sql statement
            if(Reservation.isPersistent())
                stmt.setLong( 3, Reservation.getId());
            
            inscnt = stmt.executeUpdate();
            
            if(!Reservation.isPersistent()){
                if(inscnt >=1){
                    String sql = "select last_insert_id()";
                    if(stmt.execute(sql)){
                        ResultSet r = stmt.getResultSet();
                        
                        while(r.next()){
                            id = r.getLong(1);
                            if(id>0)
                                Reservation.setId(id);
                        }
                    }
                }
                else
                    throw new RARException("ReservationManager.save: failed to save a Reservation");
            }
            else{
                if(inscnt <1)
                    throw new RARException("ReservationManager.save: failed to save a Reservation");
            }
            
        }
        
        catch (SQLException e){
            throw new RARException("ReservationManager.save: failed to save a Reservation:" + e );
        }
        
    }    

    //Restore
    public List<Reservation> restore(Reservation Reservation) throws RARException {
        String      selectASQL = "SELECT r.id, r.customer_id, r.rental_location, r.vehicle_type_id, r.pickup_time, r.length, r.cancelled" + " from Reservation r WHERE";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);
        List<Reservation> reservations = new ArrayList<Reservation>();
    	
        condition.setLength(0);
        
        query.append(selectASQL);
        
        if(Reservation.getId() >= 0)
            query.append(" AND id = " + Reservation.getId());
        else{
            if(Reservation.getId()!=-1)
                condition.append(" AND id = '" + Reservation.getId() + "'");
            if(Reservation.getCustomer()!=null)
                condition.append(" AND customer_id = '" + Reservation.getCustomer() + "'");
            if(Reservation.getRental()!=null)
                condition.append(" AND rental_location = '" + Reservation.getRental() + "'");
            if(Reservation.getVehicleType()!=null)
                condition.append(" AND vehicle_type_id = '" + Reservation.getVehicleType() + "'");
            if(Reservation.getPickupTime()!=null)
                condition.append(" AND pickup_time = '" + Reservation.getPickupTime() + "'");
            if(Reservation.getLength()!=-1)
            	condition.append(" AND length = '" + Reservation.getLength() + "'");
            /*if(Reservation.getCancelled()!=-1)
            	condition.append(" AND cancelled = '" + Reservation.getCancelled() + "'");   */    
        }
        
        try{
            
            stmt = conn.createStatement();
            
            if(stmt.execute(query.toString())){
                long    			id;
                /*Customer  			customer;
                RentalLocation    	rentalLocation;
                VehicleType    		vehicle_type_id;*/
                Date			  	pickup_time;
                int					length;
                boolean				cancelled;
                
                Reservation ReservationProxy = null;
                
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                    id = 				rs.getLong(1);
                    pickup_time = 		rs.getDate(2);
                    length = 			rs.getInt(3);
                    cancelled = 		rs.getBoolean(4);
                    
                    ReservationProxy = objectLayer.createReservation();
                    
                    reservations.add(ReservationProxy);
                }
                return reservations;
            }
            
        }
        
        catch(Exception e){
            throw new RARException("ReservationManager.restore: COuld not restore persistent objects: Root cause: " + e);
        }
     throw new RARException("ReservationManger.restore: Could not restore persistent Reservation object");   
    }//Restore
    
    
    public void delete(Reservation Reservation) throws RARException {
    	String               deleteCommentSQL = "delete from Reservation where id = ?";          
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!Reservation.isPersistent()) return;
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, Reservation.getId() );
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 1 ) {
                throw new RARException( "ReservationManager.delete: failed to delete this Reservation" );
            }
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "ReservationManager.delete: failed to delete this Reservation: " + e.getMessage() );
        }//catch
    }//delete
    
    /*
     * 
     * Customer Reservation
     *
     * 
     */
    
    //Save implementation with the customer parameter
    public void saveCustomer(Customer customer, Reservation Reservation) throws RARException {
    
        String              insertReservationSql = "insert into reservation (customer_id, pickup_time, rental_length) values (?, ?, ?)";
        String              updateReservationSql = "update Reservation set customer_id =?, pickup_time = ?, rental_length = ?";
        PreparedStatement   stmt = null;
        int                 inscnt;
        long                id;
        
        try{
            //Persistence check
            if(!Reservation.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertReservationSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateReservationSql );
           
            //Set Customer
            if(Reservation.getCustomer() != null)
            	stmt.setLong(1, Reservation.getCustomer().getId());
            else
                throw new RARException("ReservationManager.save: can't save a Reservation: Customer is undefined");
            //Set pickup time
            if(Reservation.getPickupTime() != null)
                stmt.setDate(2, (java.sql.Date) Reservation.getPickupTime());
            else
                throw new RARException("ReservationManager.save: can't save a Reservation: Pickup time is undefined");
            //Set return time
            if(Reservation.getLength() >= -1)
                stmt.setInt(3, Reservation.getLength());
            else
                throw new RARException("ReservationManager.save: can't save a Reservation: Return time is undefined");
            
            //Update Sql statement
            if(Reservation.isPersistent())
                stmt.setLong( 4, Reservation.getId());
            
            inscnt = stmt.executeUpdate();
            
            if(!Reservation.isPersistent()){
                if(inscnt >=1){
                    String sql = "select last_insert_id()";
                    if(stmt.execute(sql)){
                        ResultSet r = stmt.getResultSet();
                        
                        while(r.next()){
                            id = r.getLong(1);
                            if(id>0)
                                Reservation.setId(id);
                        }
                    }
                }
                else
                    throw new RARException("ReservationManager.save: failed to save a Reservation");
            }
            else{
                if(inscnt <1)
                    throw new RARException("ReservationManager.save: failed to save a Reservation");
            }
            
        }
        
        catch (SQLException e){
            throw new RARException("ReservationManager.save: failed to save a Reservation:" + e );
        }
        
    }//SaveCustomer
    
  //Customer Restore returning Customer with parameter reservation
    public Customer restoreCustomer(Reservation reservation) throws RARException {
        String      selectASQL = "SELECT r.id, r.customer_id, r.rental_location, r.vehicle_type_id, r.pickup_time, r.length, r.cancelled" + " from Reservation r WHERE";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);
        List<Reservation> reservations = new ArrayList<Reservation>();
    	
        condition.setLength(0);
        
        query.append(selectASQL);
        
        if(Reservation.getId() >= 0)
            query.append(" AND id = " + Reservation.getId());
        else{
            if(Reservation.getId()!=-1)
                condition.append(" AND id = '" + Reservation.getId() + "'");
            if(Reservation.getCustomer()!=null)
                condition.append(" AND customer_id = '" + Reservation.getCustomer() + "'");
            if(Reservation.getRental()!=null)
                condition.append(" AND rental_location = '" + Reservation.getRental() + "'");
            if(Reservation.getVehicleType()!=null)
                condition.append(" AND vehicle_type_id = '" + Reservation.getVehicleType() + "'");
            if(Reservation.getPickupTime()!=null)
                condition.append(" AND pickup_time = '" + Reservation.getPickupTime() + "'");
            if(Reservation.getLength()!=-1)
            	condition.append(" AND length = '" + Reservation.getLength() + "'");
            /*if(Reservation.getCancelled()!=-1)
            	condition.append(" AND cancelled = '" + Reservation.getCancelled() + "'");   */    
        }
        
        try{
            
            stmt = conn.createStatement();
            
            if(stmt.execute(query.toString())){
                long    			id;
                /*Customer  			customer;
                RentalLocation    	rentalLocation;
                VehicleType    		vehicle_type_id;*/
                Date			  	pickup_time;
                int					length;
                boolean				cancelled;
                
                Reservation ReservationProxy = null;
                
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                    id = 				rs.getLong(1);
                    pickup_time = 		rs.getDate(2);
                    length = 			rs.getInt(3);
                    cancelled = 		rs.getBoolean(4);
                    
                    ReservationProxy = objectLayer.createReservation();
                    
                    reservations.add(ReservationProxy);
                }
                return reservations;
            }
            
        }
        
        catch(Exception e){
            throw new RARException("ReservationManager.restore: COuld not restore persistent objects: Root cause: " + e);
        }
     throw new RARException("ReservationManger.restore: Could not restore persistent Reservation object");   
    }//Restore
   
  //Customer Restore returning list with customer parameter
    public List<Reservation> restoreCustomer(Customer customer) throws RARException {
        String      selectASQL = "SELECT r.id, r.customer_id, r.rental_location, r.vehicle_type_id, r.pickup_time, r.length, r.cancelled" + " from Reservation r WHERE";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);
        List<Reservation> reservations = new ArrayList<Reservation>();
    	
        condition.setLength(0);
        
        query.append(selectASQL);
        
        if(Reservation.getId() >= 0)
            query.append(" AND id = " + Reservation.getId());
        else{
            if(Reservation.getId()!=-1)
                condition.append(" AND id = '" + Reservation.getId() + "'");
            if(Reservation.getCustomer()!=null)
                condition.append(" AND customer_id = '" + Reservation.getCustomer() + "'");
            if(Reservation.getRental()!=null)
                condition.append(" AND rental_location = '" + Reservation.getRental() + "'");
            if(Reservation.getVehicleType()!=null)
                condition.append(" AND vehicle_type_id = '" + Reservation.getVehicleType() + "'");
            if(Reservation.getPickupTime()!=null)
                condition.append(" AND pickup_time = '" + Reservation.getPickupTime() + "'");
            if(Reservation.getLength()!=-1)
            	condition.append(" AND length = '" + Reservation.getLength() + "'");
            /*if(Reservation.getCancelled()!=-1)
            	condition.append(" AND cancelled = '" + Reservation.getCancelled() + "'");   */    
        }
        
        try{
            
            stmt = conn.createStatement();
            
            if(stmt.execute(query.toString())){
                long    			id;
                /*Customer  			customer;
                RentalLocation    	rentalLocation;
                VehicleType    		vehicle_type_id;*/
                Date			  	pickup_time;
                int					length;
                boolean				cancelled;
                
                Reservation ReservationProxy = null;
                
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                    id = 				rs.getLong(1);
                    pickup_time = 		rs.getDate(2);
                    length = 			rs.getInt(3);
                    cancelled = 		rs.getBoolean(4);
                    
                    ReservationProxy = objectLayer.createReservation();
                    
                    reservations.add(ReservationProxy);
                }
                return reservations;
            }
            
        }
        
        catch(Exception e){
            throw new RARException("ReservationManager.restore: COuld not restore persistent objects: Root cause: " + e);
        }
     throw new RARException("ReservationManger.restore: Could not restore persistent Reservation object");   
    }//Restore
    
    //Delete Customer Reservation
    public void deleteCustomer(Customer customer, Reservation Reservation) throws RARException {
    	String               deleteCommentSQL = "delete from Reservation where id = ?";          
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!Reservation.isPersistent()) return;
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, Reservation.getId() );
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 1 ) {
                throw new RARException( "ReservationManager.delete: failed to delete this Reservation" );
            }
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "ReservationManager.delete: failed to delete this Reservation: " + e.getMessage() );
        }//catch
    }//delete
    
    /*
     * 
     * 
     * 
     *	 Rental Reservation
     * 
     * 
     *
     */
     
  //Save Rental Reservation
    public void saveRental( Rental rental, Reservation Reservation) throws RARException {
    
        String              insertReservationSql = "insert into reservation (pickup_time, rental_length) values ( ?, ?)";
        String              updateReservationSql = "update Reservation set pickup_time = ?, rental_length = ?";
        PreparedStatement   stmt = null;
        int                 inscnt;
        long                id;
        
        try{
            //Persistence check
            if(!Reservation.isPersistent() )
                stmt = (PreparedStatement) conn.prepareStatement( insertReservationSql );
            else
                stmt = (PreparedStatement) conn.prepareStatement( updateReservationSql );
           
            //Set pickup time
            if(Reservation.getPickupTime() != null)
                stmt.setDate(1, (java.sql.Date) Reservation.getPickupTime());
            else
                throw new RARException("ReservationManager.save: can't save a Reservation: Pickup time is undefined");
            //Set return time
            if(Reservation.getLength() >= -1)
                stmt.setInt(2, Reservation.getLength());
            else
                throw new RARException("ReservationManager.save: can't save a Reservation: Return time is undefined");
            
            //Update Sql statement
            if(Reservation.isPersistent())
                stmt.setLong( 3, Reservation.getId());
            
            inscnt = stmt.executeUpdate();
            
            if(!Reservation.isPersistent()){
                if(inscnt >=1){
                    String sql = "select last_insert_id()";
                    if(stmt.execute(sql)){
                        ResultSet r = stmt.getResultSet();
                        
                        while(r.next()){
                            id = r.getLong(1);
                            if(id>0)
                                Reservation.setId(id);
                        }
                    }
                }
                else
                    throw new RARException("ReservationManager.save: failed to save a Reservation");
            }
            else{
                if(inscnt <1)
                    throw new RARException("ReservationManager.save: failed to save a Reservation");
            }
            
        }
        
        catch (SQLException e){
            throw new RARException("ReservationManager.save: failed to save a Reservation:" + e );
        }
        
    }
    
  //Rental Restore returning Rental with parameter reservation
    public Rental restoreRental(Reservation reservation) throws RARException {
        String      selectASQL = "SELECT r.id, r.customer_id, r.rental_location, r.vehicle_type_id, r.pickup_time, r.length, r.cancelled" + " from Reservation r WHERE";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);
        List<Reservation> reservations = new ArrayList<Reservation>();
    	
        condition.setLength(0);
        
        query.append(selectASQL);
        
        if(Reservation.getId() >= 0)
            query.append(" AND id = " + Reservation.getId());
        else{
            if(Reservation.getId()!=-1)
                condition.append(" AND id = '" + Reservation.getId() + "'");
            if(Reservation.getCustomer()!=null)
                condition.append(" AND customer_id = '" + Reservation.getCustomer() + "'");
            if(Reservation.getRental()!=null)
                condition.append(" AND rental_location = '" + Reservation.getRental() + "'");
            if(Reservation.getVehicleType()!=null)
                condition.append(" AND vehicle_type_id = '" + Reservation.getVehicleType() + "'");
            if(Reservation.getPickupTime()!=null)
                condition.append(" AND pickup_time = '" + Reservation.getPickupTime() + "'");
            if(Reservation.getLength()!=-1)
            	condition.append(" AND length = '" + Reservation.getLength() + "'");
            /*if(Reservation.getCancelled()!=-1)
            	condition.append(" AND cancelled = '" + Reservation.getCancelled() + "'");   */    
        }
        
        try{
            
            stmt = conn.createStatement();
            
            if(stmt.execute(query.toString())){
                long    			id;
                /*Customer  			customer;
                RentalLocation    	rentalLocation;
                VehicleType    		vehicle_type_id;*/
                Date			  	pickup_time;
                int					length;
                boolean				cancelled;
                
                Reservation ReservationProxy = null;
                
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                    id = 				rs.getLong(1);
                    pickup_time = 		rs.getDate(2);
                    length = 			rs.getInt(3);
                    cancelled = 		rs.getBoolean(4);
                    
                    ReservationProxy = objectLayer.createReservation();
                    
                    reservations.add(ReservationProxy);
                }
                return reservations;
            }
            
        }
        
        catch(Exception e){
            throw new RARException("ReservationManager.restore: COuld not restore persistent objects: Root cause: " + e);
        }
     throw new RARException("ReservationManger.restore: Could not restore persistent Reservation object");   
    }//Restore
    
  //Rental Restore returning Reservation with parameter rental
    public Reservation restoreRental(Rental rental) throws RARException {
        String      selectASQL = "SELECT r.id, r.customer_id, r.rental_location, r.vehicle_type_id, r.pickup_time, r.length, r.cancelled" + " from Reservation r WHERE";
        Statement stmt = null;
        StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);
        List<Reservation> reservations = new ArrayList<Reservation>();
    	
        condition.setLength(0);
        
        query.append(selectASQL);
        
        if(Reservation.getId() >= 0)
            query.append(" AND id = " + Reservation.getId());
        else{
            if(Reservation.getId()!=-1)
                condition.append(" AND id = '" + Reservation.getId() + "'");
            if(Reservation.getCustomer()!=null)
                condition.append(" AND customer_id = '" + Reservation.getCustomer() + "'");
            if(Reservation.getRental()!=null)
                condition.append(" AND rental_location = '" + Reservation.getRental() + "'");
            if(Reservation.getVehicleType()!=null)
                condition.append(" AND vehicle_type_id = '" + Reservation.getVehicleType() + "'");
            if(Reservation.getPickupTime()!=null)
                condition.append(" AND pickup_time = '" + Reservation.getPickupTime() + "'");
            if(Reservation.getLength()!=-1)
            	condition.append(" AND length = '" + Reservation.getLength() + "'");
            /*if(Reservation.getCancelled()!=-1)
            	condition.append(" AND cancelled = '" + Reservation.getCancelled() + "'");   */    
        }
        
        try{
            
            stmt = conn.createStatement();
            
            if(stmt.execute(query.toString())){
                long    			id;
                /*Customer  			customer;
                RentalLocation    	rentalLocation;
                VehicleType    		vehicle_type_id;*/
                Date			  	pickup_time;
                int					length;
                boolean				cancelled;
                
                Reservation ReservationProxy = null;
                
                ResultSet rs = stmt.getResultSet();
                
                while(rs.next()){
                    id = 				rs.getLong(1);
                    pickup_time = 		rs.getDate(2);
                    length = 			rs.getInt(3);
                    cancelled = 		rs.getBoolean(4);
                    
                    ReservationProxy = objectLayer.createReservation();
                    
                    reservations.add(ReservationProxy);
                }
                return reservations;
            }
            
        }
        
        catch(Exception e){
            throw new RARException("ReservationManager.restore: COuld not restore persistent objects: Root cause: " + e);
        }
     throw new RARException("ReservationManger.restore: Could not restore persistent Reservation object");   
    }//Restore
    
    //Delete Rental Reservation
    public void deleteRental(Rental rental, Reservation Reservation) throws RARException {
    	String               deleteCommentSQL = "delete from Reservation where id = ?";          
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!Reservation.isPersistent()) return;
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, Reservation.getId() );
            inscnt = stmt.executeUpdate();
            
            if( inscnt == 1 ) {
                throw new RARException( "ReservationManager.delete: failed to delete this Reservation" );
            }
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "ReservationManager.delete: failed to delete this Reservation: " + e.getMessage() );
        }//catch
    }//delete
    
}
