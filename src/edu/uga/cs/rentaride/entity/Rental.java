package edu.uga.cs.rentaride.entity;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;

/** This class represent a rental event, involving a prior reservation, a vehicle being rented, and 
 * the customer.
 *
 */
public interface Rental 
    extends Persistable
{
    /** Return the date when the vehicle in this rental was picked up.
     * @return the pickup date for this rental
     */
    public Date getPickupTime();
    
    /** Set the date when the vehicle in this rental was picked up.
     * @param pickupTime the new pickup time of this rental
     */
    public void setPickupTime( Date pickupTime );
    
    /** Return the date when the vehicle in this rental was returned.
     * @return the date when the vehicle was returned
     */
    public Date getReturnTime();
    
    /** Set the date when the vehicle in this rental was returned.
     * @param returnTime the return time of this rental
     * @throws RARException in case the return time is not later than the pickup time
     */
    public void setReturnTime( Date returnTime ) throws RARException;
    
    /** Return true if this rental was returned late; false otherwise.
     * It is a derived attribute, so no setter method is provided.
     * @return the late status of this rental
     */
    public boolean getLate();
    
    /** Return the charges applied to this rental.
     * @return the charges applied to this rental
     */
    public int getCharges();
    
    /** Set the charges applied to this rental.
     * @param charges the new charges for this rental
     * @throws RARException in case charges is non-positive
     */
    public void setCharges( int charges ) throws RARException;
    
    /** Return the reservation for this rental.
     * @return the Reservation object of this rental
     */
    public Reservation getReservation();
    
    /** Set the reservation for this rental.
     * @param reservation the new Reservation object of this rental
     * @throws RARException in case reservation is null
     */
    public void setReservation( Reservation reservation ) throws RARException;
    
    /** Return the vehicle for this rental.
     * @return the Vehicle object of this rental
     */
    public Vehicle getVehicle();
    
    /** Set the vehicle for this rental.
     * @param vehicle the new Vehicle for this rental
     * @throws RARException in case vehicle is null
     */
    public void setVehicle( Vehicle vehicle ) throws RARException;
    
    /** Return the customer involved in this rental.
     * @return the Customer object of this rental
     */
    public Customer getCustomer();
    
    /** Set the customer involved in this rental.  
     * This is a derived association, so no setter is needed.
     * @param customer the new Customer for this rental
     */
    // public void setCustomer( Customer customer );
    
    /** Return the comment associated with this rental.
     * @return the Comment associated with this rental
     */
    public Comment getComment();
    
    /** Associate a comment with this rental.
     * @param comment the Comment to be associated with this rental
     */
    public void setComment( Comment comment );
}
