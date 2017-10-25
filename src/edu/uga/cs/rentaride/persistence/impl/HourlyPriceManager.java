package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.impl.HourlyPriceImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class HourlyPriceManager {
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public HourlyPriceManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(HourlyPrice hourlyPrice) throws RARException {
    	String               insertSQL = "insert into hourlyPrice (max_hours, price, type_id values (?, ?, ?)";
        String               updateSQL = "update hourlyPrice set max_hours = ?, price = ?, type_id = ? where id = ? ";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 id;
        
        try {
        	
	        if (!hourlyPrice.isPersistent())
	            stmt = (PreparedStatement) conn.prepareStatement(insertSQL);
	        else
	            stmt = (PreparedStatement) conn.prepareStatement(updateSQL);
	        
	        //MaxHours
	        if (hourlyPrice.getMaxHours() != -1 || hourlyPrice.getMaxHours() > -1) { //or not greater than minHours, getMinHours?
	        	stmt.setInt(1, hourlyPrice.getMaxHours());
	        } else 
	        	stmt.setNull(1, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Price
	        if (hourlyPrice.getPrice() != -1 || hourlyPrice.getPrice()>-1) {
	        	stmt.setInt(2, hourlyPrice.getPrice());
	        } else 
	        	stmt.setNull(2, java.sql.Types.VARCHAR); //Throw error instead? Unsure
	        
	        //Type_id
	        if (hourlyPrice.getVehicleType() != null) {
	        	stmt.setLong(3, hourlyPrice.getVehicleType().getId());
	        } else 
	        	stmt.setNull(3, java.sql.Types.INTEGER); //Throw error instead? Unsure
	        
            inscnt = stmt.executeUpdate();
            
            if (!hourlyPrice.isPersistent()) {
                if (inscnt >= 1) {
                    String sql = "SELECT LAST_INSERT_ID()";
                    if(stmt.execute(sql)) { // statement returned a result

                        //Retrieve the result
                        ResultSet rs = stmt.getResultSet();

                        while(rs.next()) {

            				//Retrieve the last insert AUTO_INCREMENT value
                            id = rs.getLong(1);
                            if(id > 0)
                            	hourlyPrice.setId(id); // set this hourlyPrice's db id (proxy object)
                        }
                    }
                }
                else
                    throw new RARException("HourlyPriceManager.save: failed to save an Hourly Price");
            }
            else {
                if( inscnt < 1 )
                    throw new RARException("HourlyPriceManager.save: failed to save an Hourly Price"); 
            }
        }//try 
        catch (SQLException e)  {
        	e.printStackTrace();
            throw new RARException("HourlyPriceManager.save: failed to save an hourly price: " + e);
        }
        throw new RARException( "HourlyPriceManager.restore: Could not restore persistent hourly price object" );
        
    }
    
    public List<HourlyPrice> restore(HourlyPrice hourlyPrice) throws RARException {
    	return null;
    }
    
    public void delete(HourlyPriceImpl hourlyPrice) throws RARException {
    	String               deleteCommentSQL = "delete from hourlyPrice where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!hourlyPrice.isPersistent()) return; //Jump out if not persistent, nothing to be deleted
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, hourlyPrice.getId() );
            inscnt = stmt.executeUpdate();
            
            if (inscnt == 1) {
            	return;
            } else {
            	throw new RARException( "HourlyPriceManager.delete: failed to delete this hourly price" );
            }
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "HourlyPriceManager.delete: failed to delete this hourly price: " + e.getMessage() );
        }//catch
    }
}