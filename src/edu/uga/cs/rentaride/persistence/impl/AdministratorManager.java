package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleImpl;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class AdministratorManager {
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public AdministratorManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    public void save(Administrator administrator) throws RARException {
    	String               insertASql = "insert into admin (first_name, last_name, username, password, email, address, create_date, status) values ( ?, ?, ?, ?, ?, ?, ?, ?)";
        String               updateASql = "update admin set first_name = ?, last_name = ?, username = ?, password = ?, email = ?, address = ?, create_date = ?, status = ? where id = ? ";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 id;

        try {
            if (!administrator.isPersistent())
                stmt = (PreparedStatement) conn.prepareStatement(insertASql);
            else
                stmt = (PreparedStatement) conn.prepareStatement(updateASql);
            
            //First name
            if (administrator.getFirstName() != null)
                stmt.setString(1, administrator.getFirstName());
            else
            	 throw new RARException("AdministratorManager.save: can't save an Administrator: First Name is undefined");
                 //stmt.setNull( 1, java.sql.Types.VARCHAR ); <--- How do we know not to do this instead of throwing an error?
            
            //Last name
            if (administrator.getLastName() != null)
                stmt.setString(2, administrator.getLastName());
            else
            	 throw new RARException("AdministratorManager.save: can't save an Administrator: Last Name is undefined");
            
            //Username
            if (administrator.getUserName() != null)
                stmt.setString(3, administrator.getUserName());
            else
            	 throw new RARException("AdministratorManager.save: can't save an Administrator: Username is undefined");
            
            //Password
            if (administrator.getPassword() != null)
                stmt.setString(4, administrator.getPassword());
            else
            	 throw new RARException("AdministratorManager.save: can't save an Administrator: Password is undefined");
            
            //Email
            if (administrator.getEmail() != null)
                stmt.setString(5, administrator.getEmail());
            else
            	 throw new RARException("AdministratorManager.save: can't save an Administrator: Email is undefined");
            
            //Address
            if (administrator.getAddress() != null)
                stmt.setString(6, administrator.getAddress());
            else
            	 throw new RARException("AdministratorManager.save: can't save an Administrator: Address is undefined");
            
            //Date
            if (administrator.getCreatedDate() != null)
            	stmt.setDate(7, (java.sql.Date) administrator.getCreatedDate());
            else
                throw new RARException("Administrator.save: can't save a Administrator: Created Date is undefined");
            
            //User status
            if (administrator.getUserStatus() != null)
                stmt.setString( 8, administrator.getUserStatus().toString() );
            else
            	 throw new RARException("Administrator.save: Can't save an Administrator: UserStatus is not set or not persistent");
            
            //Only for update SQL statement
            if (administrator.isPersistent())
                stmt.setLong(9, administrator.getId());

            inscnt = stmt.executeUpdate();
            
            //Executing SQL query
            if (!administrator.isPersistent()) {
            	if (inscnt >= 1) { //If the query returns a result
            		String sql = "SELECT LAST_INSERT_ID()";
            		if (stmt.execute(sql)) { 
            			
            			//Retreive the ResultSet
            			ResultSet rs = stmt.getResultSet();
            			
            			while (rs.next()) {
            				
            				//Retrieve the last insert AUTO_INCREMENT value
                            id = rs.getLong(1);
                            if (id != -1) //id not "null"/-1
                                administrator.setId(id); //Set the id
            			}//while
            		}//if
            	}//if
	            else
	            	throw new RARException("AdministratorManager.save: failed to save an administrator");
	        }//if 
	        else {
	        	if (inscnt < 1)
	        		throw new RARException("AdministratorManager.save: failed to save an administrator");
	        }
        }
        catch (Exception e) {
        	throw new RARException("AdministratorManager.save: failed to save an Administrator");
        }
    }
    
    public List<Administrator> restore(Administrator administrator) throws RARException {
    	String       selectASQL = "SELECT a.first_name, a.last_name, a.username, a.password, a.email, a.address, a.create_date, a.status " +
                     " from admin a WHERE ";
		Statement    stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Administrator> admins = new ArrayList<Administrator>();
		
		condition.setLength(0);
		
		query.append(selectASQL);
		
		if (administrator != null) {
			if (administrator.getId() >= 0) {
				query.append(" AND id = " + administrator.getId());
			} else {
				if (administrator.getFirstName() != null)
					condition.append(" AND first_name = '" + administrator.getFirstName() + "'");
				
				if (administrator.getLastName() != null)
					condition.append(" AND last_name = '" + administrator.getLastName() + "'");
				
				if (administrator.getUserName() != null)
					condition.append(" AND username = '" + administrator.getUserName() + "'");
				
				if (administrator.getPassword() != null)
					condition.append(" AND password = '" + administrator.getPassword() + "'");
				
				if (administrator.getEmail() != null)
					condition.append(" AND email = '" + administrator.getEmail() + "'");
				
				if (administrator.getAddress() != null)
					condition.append(" AND address = '" + administrator.getAddress() + "'");

				if (administrator.getCreatedDate() != null)
					condition.append(" AND create_date = '" + administrator.getCreatedDate() + "'");
				
				if (administrator.getUserStatus() != null)
					condition.append(" AND status = '" + administrator.getUserStatus() + "'");
			} //else
		}//if
		
		try {
			
			stmt = conn.createStatement();
			
			if (stmt.execute(query.toString())) {
				String     firstName;
			    String     lastName;
			    String     userName;
			    String     password;
			    String     email;
			    String     address;
			    Date       createDate;
			    UserStatus status;
				Administrator adminProxy = null;
			    
				ResultSet rs = stmt.getResultSet();
				
				while (rs.next()) {
					firstName = rs.getString(1);
					lastName = rs.getString(2);
					userName = rs.getString(3);
					password = rs.getString(4);
					email = rs.getString(5);
					address = rs.getString(6);
					createDate = rs.getDate(7);
					//status = rs.getObject(8);
					
					adminProxy = objectLayer.createAdministrator();
					adminProxy.setFirstName(firstName);
					adminProxy.setLastName(lastName);
					adminProxy.setUserName(userName);
					adminProxy.setPassword(password);
					adminProxy.setEmail(email);
					adminProxy.setAddress(address);
					adminProxy.setCreateDate(createDate);
					adminProxy.setUserStatus(UserStatus.ACTIVE);
					
					admins.add(adminProxy);
				}
				return admins;
			}
		} catch (SQLException e) {
    		throw new RARException("AdministratorManager.restore: Could not restore persistent objects; Root cause: " + e );
    	}
    	throw new RARException( "AdministratorManager.restore: Could not restore persistent Administrator object" );
		
    }//restore()
    
    public void delete(Administrator administrator) throws RARException {
    	String               deleteCommentSQL = "delete from admin where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!administrator.isPersistent()) return; //Jump out if not persistent, nothing to be deleted
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, administrator.getId() );
            inscnt = stmt.executeUpdate();
            
            if (inscnt == 1) {
            	return;
            } else {
                throw new RARException( "AdministratorManager.delete: failed to delete this administrator" );
            }
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "AdministratorManager.delete: failed to delete this administrator: " + e.getMessage() );
        }//catch
    }
}
