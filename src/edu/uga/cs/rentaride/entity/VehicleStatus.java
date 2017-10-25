package edu.uga.cs.rentaride.entity;

/** This enum represents possible values for the status of a vehicle in the Rent-A-Ride system.
 *
 */
public enum VehicleStatus {
    /**
     * the vehicle is currently in its rental location
     */
    INLOCATION, 
    
    /**
     * the vehicle is currently rented out and not in its rental location
     */
    INRENTAL 
}
