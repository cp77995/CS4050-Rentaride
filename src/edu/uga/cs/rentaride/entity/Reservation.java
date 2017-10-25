package edu.uga.cs.rentaride.entity;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;


/** This class represents a reservation made by a Rent-A-Ride customer, for a vehicle type at a
 * specific rental location to be rented at a specific date and time.  The requested duration of
 * a rental must be expressed as a positive number of hours, which is less than or equal to 72.
 * Once the customer actually rents a vehicle, the reservation will involve a Rental object.
 *
 */
public interface Reservation 
    extends Persistable
{
    /** Return the intended pickup time.
     * @return the pickup time for this reservation
     */
    public Date getPickupTime();
    
    /** Set the intended pickup time.
     * @param pickupTime the new pickup time for this reservation
     * @throws RARException in case the pickup time is in the past
     */
    public void setPickupTime( Date pickupTime ) throws RARException;
    
    /** Return the intended rental length (in hours).
     * @return the intended rental length (in hours)
     */
    public int getLength();
    
    /** Set the intended rental length (in hours).
     * @param length the new intended rental length (in hours)
     * @throws RARException in case length time is illegal
     */
    public void setLength( int length ) throws RARException;
    
    /** Return the customer who made this reservation.
     * @return the customer who made this reservation
     */
    public Customer getCustomer();
    
    /** Set the customer who made this reservation.
     * @param customer the customer who made this reservation
     * @throws RARException in case customer is null
     */
    public void setCustomer( Customer customer ) throws RARException;
    
    /** Return the vehicle type of this reservation.
     * @return VehicleType for which this reservation was made
     */   
    public VehicleType getVehicleType();
    
    /** Set the new vehicle type for this reservation.
     * @param vehicleType the new vehicle type for this reservation
     * @throws RARException in case vehicleType is null
     */ 
    public void setVehicleType( VehicleType vehicleType ) throws RARException;
    
    /** Return the rental location for which this reservation was made.
     * @return the RentalLocation for which this reservation was made
     */
    public RentalLocation getRentalLocation();
    
    /** Set the new rental location for this reservation.
     * @param rentalLocation the new rental location for this reservation
     * @throws RARException in case rentalLocation is null
     */  
    public void setRentalLocation( RentalLocation rentalLocation ) throws RARException;
    
    /** Return the rental that was based on this reservation.
     * @return the Rental based on this reservation
     */
    public Rental getRental();

    /** Set the rental that was based on this reservation.
     * @param rental the new Rental based on this reservation
     */
    public void setRental( Rental rental );
}
