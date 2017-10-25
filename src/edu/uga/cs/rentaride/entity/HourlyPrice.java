package edu.uga.cs.rentaride.entity;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;

/** This class represents an hourly price set for a specific time interval.
 * The price value is represented in Cents, not Dollars.
 *
 */
public interface HourlyPrice
    extends Persistable
{   
    /** Return the maximum hours for this price setting.
     * @return the maximum hours for this price setting
     */
    public int getMaxHours();
    
    /** Set the new maximum hours for this price setting.
     * @param maxHours the new maximum hours for this price setting
     * @throws RARException in case the maxHours value is not positive or is not greater than minHours
     */
    public void setMaxHours( int maxHours ) throws RARException;
    
    /** Return the price for this hourly price setting (value is in Cents).
     * @return the price of this hourly price setting
     */
    public int getPrice();
    
    /** Set the new price for this hourly price setting (must be in Cents).
     * @param price the new price of this hourly setting (in Cents)
     * @throws RARException in case the price value is non-positive
     */
    public void setPrice( int price ) throws RARException;
    
    /** Return the VehicleType of this price setting.
     * @return the VehicleType of this price setting
     */
    public VehicleType getVehicleType();

    /** Set the new VehicleType of this price setting.
     * @param vehicleType the new VehicleType for this price setting
     * @throws RARException in case the vehicleType is null
     */
    public void setVehicleType( VehicleType vehicleType ) throws RARException;
}
