package edu.uga.cs.rentaride.entity;

/** This enum represents possible values for the status of a user in the Rent-A-Ride system.
*
*/
public enum UserStatus {
    /**
     * the user is currently active and in good standing
     */
    ACTIVE,
    
    /**
     * the user has been voluntarily removed (user action) and is no longer active
     */
    CANCELLED,  
    
    /**
     * the user has been involuntarily terminated (administrator action) and is no longer active
     */
    TERMINATED
}
