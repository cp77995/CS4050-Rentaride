package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class RentalLocationManager {
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public RentalLocationManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(RentalLocation RentalLocation) throws RARException {
    	String               insertSQL = "insert into RentalLocation (name, address, capacity values (?, ?, ?)";
        String               updateSQL = "update RentalLocation set name = ?, address = ?, capacity = ? where id = ? ";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 id;
        
        try {
	        if( !RentalLocation.isPersistent() )
	            stmt = (PreparedStatement) conn.prepareStatement(insertSQL);
	        else
	            stmt = (PreparedStatement) conn.prepareStatement(updateSQL);
	        
	        //Name
	        if (RentalLocation.getName() != null) { //or non-unique
	        	stmt.setString(1, RentalLocation.getName());
	        } else 
	        	stmt.setNull(1, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Address
	        if (RentalLocation.getAddress() != null) {
	        	stmt.setString(2, RentalLocation.getAddress());
	        } else 
	        	stmt.setNull(2, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Capacity
	        if (RentalLocation.getCapacity() != -1) {
	        	stmt.setInt(3, RentalLocation.getCapacity());
	        } else 
	        	stmt.setNull(3, java.sql.Types.INTEGER); //Throw error instead? Unsure
	        
	        
            inscnt = stmt.executeUpdate();
            
            if (!RentalLocation.isPersistent()) {
                if (inscnt >= 1) {
                    String sql = "SELECT LAST_INSERT_ID()";
                    if(stmt.execute(sql)) { // statement returned a result

                        //Retrieve the result
                        ResultSet rs = stmt.getResultSet();

                        while(rs.next()) {

            				//Retrieve the last insert AUTO_INCREMENT value
                            id = rs.getLong(1);
                            if(id > 0)
                            	RentalLocation.setId(id); // set this RentalLocation's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RARException("RentalLocationManager.save: failed to save a rental location");
            }
            else {
                if( inscnt < 1 )
                    throw new RARException("RentalLocationManager.save: failed to save a rental location"); 
            }
        }//try 
        catch (SQLException e)  {
        	e.printStackTrace();
            throw new RARException("RentalLocationManager.save: failed to save an rental location: " + e);
        }
        throw new RARException( "RentalLocationManager.restore: Could not restore persistent rental location object" );
        
    }
    
    //Needs to be completed
    public List<RentalLocation> restore(RentalLocation RentalLocation) throws RARException {
    	return null;
    }
    
    public void delete(RentalLocation RentalLocation) throws RARException {
    	String               deleteCommentSQL = "delete from RentalLocation where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!RentalLocation.isPersistent()) return; //Jump out if not persistent, nothing to be deleted
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, RentalLocation.getId() );
            inscnt = stmt.executeUpdate();
            
            if (inscnt == 1) {
            	return;
            } else {
            	throw new RARException( "RentalLocationManager.delete: failed to delete this rental location" );
            }
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "RentalLocationManager.delete: failed to delete this rental location: " + e.getMessage() );
        }//catch
    }
}