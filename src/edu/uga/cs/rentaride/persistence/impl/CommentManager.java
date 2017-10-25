package edu.uga.cs.rentaride.persistence.impl;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.impl.CommentImpl;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImpl;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentManager {
	
	private ObjectLayer objectLayer = null;
    private Connection  conn = null;
	
    public CommentManager(Connection conn, ObjectLayer objectLayer) {
        this.conn = conn;
        this.objectLayer = objectLayer;
    }
    
    //SAVE
    public void save(Comment comment) throws RARException {
    	
    	String               insertCommentSQL = "INSERT INTO comment (rental_id, text, date) values (?, ?, ?)";
    	String               updateCommentSQL = "UPDATE comment SET rental_id = ?, text = ?, date = ? WHERE id = ?";
    	PreparedStatement    stmt = null;
    	int                  inscnt;
    	long                 id;
    	
    	try {
    		
    		//Check persistence of the Comment
    		if (!comment.isPersistent()) {
    			stmt = (PreparedStatement) conn.prepareStatement(insertCommentSQL);
    		} else {
    			stmt = (PreparedStatement) conn.prepareStatement(updateCommentSQL);
    		}
    		
    		if( comment.getCustomer() == null || comment.getRental() == null )
                throw new RARException( "CommentManager.save: Attempting to save a Comment with no Customer or Rental defined" );
            if( !comment.getCustomer().isPersistent() || !comment.getRental().isPersistent() )
                throw new RARException( "CommentManager.save: Attempting to save a Comment where either Customer or Rental are not persistent" );
    		
//    		//Set id (1)
//    		if (comment.isPersistent())
//                stmt.setLong(1, comment.getId());
    		
    		//Set rental_id (1)
    		if (comment.getRental() != null)
    			stmt.setLong(3, comment.getRental().getId());
    		else 
    			throw new RARException( "Comment.save: can't save a Comment: Created Rental undefined" );
    		
    		//Set text (2)
    		if (comment.getText() != null)
    			stmt.setString(2, comment.getText());
    		else 
    			throw new RARException( "Comment.save: can't save a Comment: Created Rental undefined" );
    		
    		//Set date (3)
    		if (comment.getDate() != null)
    			stmt.setDate(3, (java.sql.Date) comment.getDate());
    		else 
    			throw new RARException( "Comment.save: can't save a Comment: Created Date undefined" );

    		//Execute the update, store how many rows were added into inscnt
            inscnt = stmt.executeUpdate();
            
            if(!comment.isPersistent()) {
                if(inscnt >= 1) {
                    
                	String sql = "SELECT LAST_INSERT_ID()";
                    
                    if(stmt.execute(sql)) {

                    	//Retrieve the ResultSet
                        ResultSet rs = stmt.getResultSet();

                        //Note: only first row used
                        while(rs.next()) {

                            //Retrieve the last insert AUTO_INCREMENT value
                            id = rs.getLong( 1 );
                            if (id > 0) //id not "null"
                                comment.setId(id); //Set the id
                        }//while
                    }//if
                }//if
                else
                    throw new RARException( "CommentManager.save: failed to save a Comment" );
            }//if
            else {
                if (inscnt < 1)
                    throw new RARException( "CommentManager.save: failed to save a Comment" ); 
            }//else
    		
    	}//try
    	catch (SQLException e) {
    		e.printStackTrace();
    		throw new RARException( "CommentManager.save: failed to save a comment" + e );
    	}//catch
    }
    
    //RESTORE list
    public List<Comment> restore(Comment comment) throws RARException {
    	String selectCommentSQL = "SELECT * FROM comment c";
    	Statement stmt = null;
    	StringBuffer query = new StringBuffer(100);
        StringBuffer condition = new StringBuffer(100);
    	List<Comment> comments = new ArrayList<Comment>();
    	
    	condition.setLength(0);
        
    	query.append(selectCommentSQL);
    	
    	//Query building. Check attributes
    	if (comment != null) {
    		if (comment.getId() >= 0) 
    			query.append(" AND c.id = " + comment.getId());
    		else if (comment.getRental() != null) 
    			query.append(" AND c.rental_id = " + comment.getRental().getId());
    		else {
    			if (comment.getDate() != null)
                    condition.append( " and c.commentDate = '" + comment.getDate() + "'" );   
                if (comment.getText() != null)
                    condition.append( " and c.comment = '" + comment.getText() + "'" );
                if (condition.length() > 0) 
                	query.append(condition);
    		}//else
    	}//if
    	
    	//Execute the query, then build list to return.
    	try {
    		stmt = conn.createStatement();
    		
    		if (stmt.execute(query.toString())) {
    			long id;
    			//long customer_id;
    			//long rental_id;
    			String text;
    			Date date;
    			Comment comProxy = null;
    			
    			ResultSet rs = stmt.getResultSet();
    			
    			while(rs.next()) {
    				id = rs.getLong(1);
    				//customer_id = rs.getLong(2);
    				//rental_id = rs.getLong(3);
    				text = rs.getString(4);
    				date = rs.getDate(5);
    				
    				comProxy = objectLayer.createComment();
    				comProxy.setId(id);
    				comProxy.setText(text);
    				comProxy.setDate(date);
    				
    				comments.add(comProxy);
    			}
    			
    			return comments;
    	
    		}//if
    		
    	}//try 
    	catch (Exception e) {
    		throw new RARException("CommentManager.restore: Could not restore persistent objects; Root cause: " + e );
    	}
    	throw new RARException( "CommentManager.restore: Could not restore persistent Comment object" );
    }
    
    //DELETE
    public void delete(Comment comment) throws RARException {
    	
    	String               deleteCommentSQL = "delete from comment where id = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        
        if(!comment.isPersistent()) return; //Jump out if not persistent, nothing to be deleted
        
        try {
            
            stmt = (PreparedStatement) conn.prepareStatement( deleteCommentSQL );
            stmt.setLong( 1, comment.getId() );
            inscnt = stmt.executeUpdate();
            
            if (inscnt == 1) 
            	return;
            else 
                throw new RARException( "CommentManager.delete: failed to delete this Comment" );
            
        }//try 
        catch( SQLException e ) {
            throw new RARException( "CommentManager.delete: failed to delete this Comment: " + e.getMessage() );
        }//catch
        
    }//delete

    //NEED TO DO
	public Rental restoreRentalComment(Comment comment) {
		return null;
	}
    
}//CommentManager