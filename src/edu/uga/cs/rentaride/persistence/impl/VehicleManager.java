package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class VehicleManager {
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public VehicleManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(Vehicle vehicle) throws RARException {
    	String               insertSQL = "insert into vehicle (make, model, year, mileage, tag, last_serviced, type_id, location_id, condition, status values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String               updateSQL = "update vehicle set make = ?, model = ?, year = ?, mileage = ?, tag = ?, last_serviced = ?, type_id = ?, location_id = ?, condition = ?, status = ? where id = ? ";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 id;
        
        try {
	        if( !vehicle.isPersistent() )
	            stmt = (PreparedStatement) conn.prepareStatement(insertSQL);
	        else
	            stmt = (PreparedStatement) conn.prepareStatement(updateSQL);
	        
	        //Make
	        if (vehicle.getMake() != null) {
	        	stmt.setString(1, vehicle.getMake());
	        } else 
	        	stmt.setNull(1, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Model
	        if (vehicle.getModel() != null) {
	        	stmt.setString(2, vehicle.getModel());
	        } else 
	        	stmt.setNull(2, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Year
	        if (vehicle.getYear() != -1) {
	        	stmt.setInt(3, vehicle.getYear());
	        } else 
	        	stmt.setNull(3, java.sql.Types.INTEGER); //Throw error instead? Unsure
	        
	        //Mileage
	        if (vehicle.getMileage() != -1) {
	        	stmt.setInt(4, vehicle.getMileage());
	        } else 
	        	stmt.setNull(4, java.sql.Types.INTEGER); //Throw error instead? Unsure
	        
	        //Tag
	        if (vehicle.getRegistrationTag() != null) {
	        	stmt.setString(5, vehicle.getRegistrationTag());
	        } else 
	        	stmt.setNull(5, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Last_serviced
	        if (vehicle.getLastServiced() != null) {
	        	stmt.setDate(6, (Date) vehicle.getLastServiced());
	        } else 
	        	stmt.setNull(6, java.sql.Types.DATE); //Throw error instead? Unsure
	        
	        //Type_id
	        if (vehicle.getVehicleType() != null) {
	        	stmt.setLong(7, vehicle.getVehicleType().getId());
	        } else 
	        	stmt.setNull(7, java.sql.Types.INTEGER); //Throw error instead? Unsure
	        
	        //Location_id
	        if (vehicle.getRentalLocation() != null) {
	        	stmt.setLong(8, vehicle.getRentalLocation().getId());
	        } else 
	        	stmt.setNull(8, java.sql.Types.INTEGER); //Throw error instead? Unsure
	        
	        //Condition
	        if (vehicle.getCondition() != null) {
	        	stmt.setString(9, vehicle.getCondition().toString());
	        } else 
	        	stmt.setNull(9, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Status
	        if (vehicle.getStatus() != null) {
	        	stmt.setString(10, vehicle.getStatus().toString());
	        } else 
	        	stmt.setNull(10, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
            inscnt = stmt.executeUpdate();
            
            if (!vehicle.isPersistent()) {
                if (inscnt >= 1) {
                    String sql = "SELECT LAST_INSERT_ID()";
                    if(stmt.execute(sql)) { // statement returned a result

                        //Retrieve the result
                        ResultSet rs = stmt.getResultSet();

                        while(rs.next()) {

            				//Retrieve the last insert AUTO_INCREMENT value
                            id = rs.getLong(1);
                            if(id > 0)
                            	vehicle.setId(id); // set this vehicle's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RARException("VehicleManager.save: failed to save a Vehicle");
            }
            else {
                if( inscnt < 1 )
                    throw new RARException("VehicleManager.save: failed to save a Vehicle"); 
            }
        }//try 
        catch (SQLException e)  {
        	e.printStackTrace();
            throw new RARException("VehicleManager.save: failed to save a Person: " + e);
        }
        throw new RARException( "VehicleManager.restore: Could not restore persistent Vehicle object" );
        
    }
    
    public List<Vehicle> restore(Vehicle modelVehicle) throws RARException {
    	return null;
    }
    
    public void delete(Vehicle vehicle) throws RARException {
    	String               deleteCommentSQL = "delete from vehicle where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!vehicle.isPersistent()) return; //Jump out if not persistent, nothing to be deleted
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, vehicle.getId() );
            inscnt = stmt.executeUpdate();
            
            if (inscnt == 1) {
            	return;
            } else {
            	throw new RARException( "VehicleManager.delete: failed to delete this vehicle" );
            }
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "VehicleManager.delete: failed to delete this vehicle: " + e.getMessage() );
        }//catch
    }

//	public void delete(Vehicle vehicle, Rental rental) {
//		String               deleteCommentSQL = "delete from vehicle where rental_id = ?";              
//        PreparedStatement    stmt = null;
//        int                  inscnt;
//        int					 rental_id = (int) rental.getId();
//        
//        if(!vehicle.isPersistent()) return; //Jump out if not persistent, nothing to be deleted
//        
//        try {
//            
//            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
//            stmt.setLong(1, vehicle.getId());
//            inscnt = stmt.executeUpdate();
//            
//            if (inscnt == 1) {
//            	return;
//            } else {
//            	throw new RARException( "VehicleManager.delete: failed to delete this vehicle" );
//            }
//            
//        }//try 
//        catch( SQLException e ) {
//            throw new RARException( "VehicleManager.delete: failed to delete this vehicle: " + e.getMessage() );
//        }//catch
//	}
}
