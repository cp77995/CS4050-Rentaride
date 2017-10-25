package edu.uga.cs.rentaride.entity;


import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;

/** This class represents a rental location in the Rent-A-Ride system.
 *
 */
public interface RentalLocation
    extends Persistable
{
    /** Return the name of this rental location
     * @return the name of this rental location
     */
    public String getName();
    
    /** Set the name of this rental location
     * @param name the new name of this rental location
     * @throws RARException in case name is non-unique or null
     */
    public void setName( String name ) throws RARException;
    
    /** Return the address of this rental location
     * @return the address of this rental location
     */
    public String getAddress();
    
    /** Set the address of this rental location
     * @param address the new address for this rental location
     */
    public void setAddress( String address );
    
    /** Return the capacity of this rental location
     * @return the capacity of this rental location
     */
    public int getCapacity();
    
    /** Set the capacity of this rental location
     * @param capacity the new capacity of this rental location
     * @throws RARException in case capacity is non-positive
     */
    public void setCapacity( int capacity ) throws RARException;
}
