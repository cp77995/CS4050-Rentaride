package edu.uga.cs.rentaride.entity;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;



/** This class represents a vehicle maintained by the Rent-A-Ride system.  In addition to
 * several vehicle attributes, each vehicle
 * has a vehicle type and is assigned to a rental location.  
 *
 */
public interface Vehicle
    extends Persistable
{
    /** Return the make of this vehicle.
     * @return the make of this vehicle
     */
    public String getMake();
    
    /** Set the make of this vehicle.
     * @param make the new make of this vehicle
     */
    public void setMake( String make );
    
    /** Return the model of this vehicle.
     * @return the model of this vehicle
     */
    public String getModel();
    
    /** Set the model of this vehicle.
     * @param model the new model of this vehicle
     */
    public void setModel( String model );
    
    /** Return the year of this vehicle.
     * @return the year of this vehicle
     */
    public int getYear();
    
    /** Set the year of this vehicle.
     * @param year the new year of this vehicle
     * @throws RARException in case year is non-positive
     */
    public void setYear( int year ) throws RARException;
    
    /** Return the registration tag of this vehicle.
     * @return the registration tag of this vehicle
     */
    public String getRegistrationTag();
    
    /** Set the registration tag of this vehicle.
     * @param registrationTag the new registration tag of this vehicle
     */
    public void setRegistrationTag( String registrationTag );
    
    /** Return the mileage of this vehicle.
     * @return the mileage of this vehicle
     */
    public int getMileage();
    
    /** Set the mileage of this vehicle.
     * @param mileage the new mileage of this vehicle
     * @throws RARException in case mileage is non-positive
     */
    public void setMileage( int mileage ) throws RARException;
    
    /** Return the last service date of this vehicle.
     * @return the last service date of this vehicle
     */
    public Date getLastServiced();
    
    /** Set the last service date of this vehicle.
     * @param lastServiced new last service date for this vehicle
     */
    public void setLastServiced( Date lastServiced );
    
    /** Return the status of this vehicle (INLOCATION or INRENTAL)
     * @return status of this vehicle
     */
    public VehicleStatus getStatus();
    
    /** Set the status of this vehicle (must be INLOCATION or INRENTAL)
     * @param status the new status of this vehicle (must be INLOCATION or INRENTAL)
     */
    public void setStatus( VehicleStatus status );

    /** Return the condition of this vehicle (GOOD or NEEDSMAINTENANCE)
     * @return the condition of this vehicle
     */
    public VehicleCondition getCondition();
    
    /** Set the condition of this vehicle (must be GOOD or NEEDSMAINTENANCE)
     * @param condition the new condition of this vehicle (must be GOOD or NEEDSMAINTENANCE)
     */
    public void setCondition( VehicleCondition condition );
    
    /** Return the vehicle type for this vehicle.
     * @return the VehicleType representing the type of this vehicle
     */
    public VehicleType getVehicleType();
    
    /** Set the vehicle type for this vehicle.
     * @param vehicleType the new VehicleType representing the type of this vehicle
     */
    public void setVehicleType( VehicleType vehicleType );
    
    /** Return the rental location of this vehicle.
     * @return the rental location of this vehicle
     */
    public RentalLocation getRentalLocation();
    
    /** Set the rental location of this vehicle.
     * @param rentalLocation the new rental location of this vehicle
     * @throws RARException in case rentalLocation is null
     */
    public void setRentalLocation( RentalLocation rentalLocation ) throws RARException;
    
    /** Return a list of all rentals which used this vehicle.
     * @return a list of all rentals for this vehicle
     */
    public List<Rental> getRentals();
}
