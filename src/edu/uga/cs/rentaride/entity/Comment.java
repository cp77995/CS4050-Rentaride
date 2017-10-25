package edu.uga.cs.rentaride.entity;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;


/** This class represents a comment made by a specific Customer regarding a specific Rental experience.
 *
 */
public interface Comment 
    extends Persistable
{
    /** Return the text of this comment.
     * @return the text of this comment.
     */
    public String getText();
    
    /** Set the new text of this comment.
     * @param text a String which is a text of this comment
     */
    public void setText( String text );
    
    /** Return the date of this comment.
     * @return the date of this comment
     */
    public Date getDate();
    
    /** Set the date of this comment.
     * @param date the new date of this comment
     */
    public void setDate( Date date );
    
    /** Return the Rental object this comment is linked to.
     * @return the Rental object for this comment.
     */
    public Rental getRental();
    
    /** Set the new Rental object linked to this comment.
     * @param rental the new Rental object this comment should be linked to.
     * @throws RARException in case the rental value is null
     */
    public void setRental( Rental rental ) throws RARException;
    
    /** Returns the customer who commented on a rental.
     * @return the Customer who made this comment
     */
    public Customer getCustomer();
    
    /** Set the customer for this Comment.
     * It is a derived association, so there is no setter method for this value.
     * @param customer the customer who made the comment
     */
    // public void setCustomer( Customer customer );
}
