package edu.uga.cs.rentaride.entity;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;

/** This class represents the configuration parameters of the Rent-A-Ride system.
 * Note that the prices are always expressed in Cents, not Dollars.
 * This is a singleton class.
 *
 */
public interface RentARideParams 
    extends Persistable
{
    /** Return the current price of the Rent-A-Ride membership.
     * @return the Rent-A-Ride current price (in cents) of the Rent-A-Ride membership
     */
    public int getMembershipPrice();
    
    /** Set the price of the Rent-A-Ride membership.
     * @param membershipPrice the new price (in cents) of the Rent-A-Ride membership
     * @throws RARException in case membershipPrice is non-positive
     */
    public void setMembershipPrice( int membershipPrice ) throws RARException;
    
    /** Return the current late fee of the Rent-A-Ride membership.
     * @return the Rent-A-Ride current late fee (in cents)
     */
    public int getLateFee();
    
    /** Set the late fee of the Rent-A-Ride system.
     * @param lateFee the new late fee (in cents)
     * @throws RARException in case membershipPrice is negative
     */
    public void setLateFee( int lateFee ) throws RARException;
}
