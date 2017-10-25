package edu.uga.cs.rentaride.object;

import java.util.Date;
import java.util.List;


import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;




/**
 * This is the interface to the Object Layer subsystem of the Rent-A-Ride system. The interface provides operations
 * realizing the life-cycle of both <i>entity objects</i> and <i>links</i> interconnecting them.
 * <p>
 * For each entity <code><i>Class</i></code>, there are four operations: <code>create<i>Class</i></code>, 
 * <code>find<i>Class</i></code>, <code>store<i>Class</i></code>, and <code>delete<i>Class</i></code>.
 * <p>
 * The <code>create<i>Class</i></code> operations are factory method-type operations to create 
 * new entity instances. There are two versions of this operation: one with arguments, which gives 
 * the initial attribute values, and one without (argument-less). 
 * The one with arguments is used to create a new entity instance which has not been persisted yet.  
 * Such an instance must be stored in the persistent data store before the use case terminates.
 * The version with no arguments creates an uninitialized entity object and should be used as the
 * first step when restoring the persistent entity object from the persistent data store (suitable
 * setter methods should be used to set the attribute values, as retrieved from the persistent
 * data store. 
 * <p>
 * Furthermore, the <code>create<i>Class</i></code> operations for classes participating in associations with 
 * other classes, and where the multiplicity on the other end of the association is one, have parameters
 * to provide the linked objects on the other end of the association.  Note, that a class participating in
 * an association with a multiplicity of one with another class cannot exist without a link to one other
 * object on the other end.  Therefore, such a link must be established at the time an object is created.
 * <p>
 * The <code>find<i>Class</i></code> operations locate objects satisfying given search criteria.  Each such operation 
 * returns a List with the located object instances.  Each of these operations accepts a single <code>model<i>Class</i></code>
 * argument. If the argument provided on a call to one of these methods is null, it indicates that all objects
 * of a given class should be returned (the search criteria are empty, and so all objects vacuously satisfy the
 * search criteria).  On the other hand, a given <code>model<i>Class</i></code> argument may have all, or just some 
 * of the class attributes provided.  In this case, the <code>find<i>Class</i></code> operation should return
 * only the matching objects, i.e., the objects with the exact same values for all of the provided
 * attribute values in the model object.  More specifically: 
 * <ul>
 *   <li>if the <code>model<i>Class</i></code> argument is null, all objects of the given class are returned;</li>
 *   <li>if the persistent identifier attribute is provided, only one object is returned;</li>
 *   <li>if some (or even all) of the attributes are provided, only objects with the same attribute values are returned.</li>
 * </ul>
 * <p>
 * The <code>store<i>Class</i></code> operations store the argument object instance in the persistent data store.  If an object
 * being stored is already persistent (<code>isPersistent()</code> returns <code>true</code>), the object is updated.  
 * Otherwise, the object is stored in the persistent data store for the first time and its persistent object identifier is set.
 * <p>
 * The <code>delete<i>Class</i></code> operations remove the argument object instance.  While the argument (proxy) object may
 * still exist in the JVM that created the proxy, but the persistent object will be deleted and the subsequent
 * <code>find<i>Class</i></code> operation call with the same persistent identifier should return an empty List. 
 */
public interface ObjectLayer
{
    /**
     * Create a new Administrator object, given the set of initial attribute values.
     * The UserStatus of the new Administrator object is UserStatus.ACTIVE.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user name (login name)
     * @param password the password
     * @param email the email address
     * @param address the address
     * @param createDate the creation date
     * @return the new Administrator object instance with the given attribute values, UserStatus is UserStatus.ACTIVE
     * @throws RARException in case the userName is non-unique
     */
    public Administrator createAdministrator( String firstName, String lastName, String userName, 
                                              String password, String email, String address, Date createDate ) throws RARException;

    /**
     * Create a new Administrator object with undefined attribute values.
     * The UserStatus of the new Administrator object is UserStatus.ACTIVE.
     * @return the new Administrator object instance
     */
    public Administrator createAdministrator(); 
    
    /**
     * Return an List of Administrator objects satisfying the search criteria given in the modelAdministrator object.
     * @param modelAdministrator a model Administrator object specifying the search criteria
     * @return an List of the located Administrator objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<Administrator> findAdministrator( Administrator modelAdministrator ) throws RARException;
    
    /**
     * Store a given Administrator object in persistent data store.
     * @param administrator the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeAdministrator( Administrator administrator ) throws RARException;
    
    /**
     * Delete this Administrator object.
     * @param administrator the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteAdministrator( Administrator administrator ) throws RARException; 
    
    /**
     * Create a new Customer object, given the set of initial attribute values.
     * The UserStatus of the new Customer object is UserStatus.ACTIVE.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user (login) name
     * @param password the password
     * @param email the email address
     * @param address the address
     * @param createDate the date when the customer has been created
     * @param membershipExpiration the date when the membership expires
     * @param licenseState the issuing state of the driver's license
     * @param licenseNumber the license number
     * @param cardNumber the credit card number
     * @param cardExpiration the expiration date of the credit card
     * @return the new Customer object instance with the given attribute values, UserStatus is UserStatus.ACTIVE
     * @throws RARException in case the userName is non-unique
     */
    public Customer createCustomer( String firstName, String lastName, String userName, String password,
            String email, String address, Date createDate, Date membershipExpiration, String licenseState, 
            String licenseNumber, String cardNumber, Date cardExpiration ) throws RARException;

    /**
     * Create a new Customer object with undefined attribute values.
     * The UserStatus of the new Administrator object is UserStatus.ACTIVE.
     * @return the new Customer object instance
     */
    public Customer createCustomer(); 
    
    /**
     * Return an List of Customer objects satisfying the search criteria given in the modelCustomer object.
     * @param modelCustomer a model Customer object specifying the search criteria
     * @return an List of the located Customer objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<Customer> findCustomer( Customer modelCustomer ) throws RARException;
    
    /**
     * Store a given Customer object in persistent data store.
     * @param customer the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeCustomer( Customer customer ) throws RARException;
    
    // No need to have a delete Customer method, as a Customer will be "removed" by changing UserStatus
    /**
     * Delete this Customer object.
     * @param customer the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    // public void deleteCustomer( Customer customer ) throws RARException;      

    /**
     * Create a new RentalLocation object, given the set of initial attribute values.
     * @param name the name of the location
     * @param address the address of the location
     * @param capacity the capacity of the location
     * @return the new RentalLocation object instance with the given attribute values
     * @throws RARException in case the name is non-unique or the capacity is non-positive
     */
    public RentalLocation createRentalLocation( String name, String address, int capacity ) throws RARException;

    /**
     * Create a new RentalLocation object with undefined attribute values.
     * @return the new RentalLocation object instance
     */
    public RentalLocation createRentalLocation();

    /**
     * Return an List of RentalLocation objects satisfying the search criteria given in the modelRentalLocation object.
     * @param modelRentalLocation a model RentalLocation object specifying the search criteria
     * @return an List of the located RentalLocation objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<RentalLocation> findRentalLocation( RentalLocation modelRentalLocation ) throws RARException;
    
    /**
     * Store a given RentalLocation object in persistent data store.
     * @param rentalLocation the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeRentalLocation( RentalLocation rentalLocation ) throws RARException;
    
    /**
     * Delete this RentalLocation object.
     * @param rentalLocation the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteRentalLocation( RentalLocation rentalLocation ) throws RARException;    

    /**
     * Create a new Reservation object, given the set of initial attribute values.
     * @param pickupTime the requested pickup time
     * @param rentalLength the length of the requested rental (in hours)
     * @param vehicleType the type of the vehicle reserved
     * @param rentalLocation the location for this reservation
     * @param customer the customer making the reservation
     * @return the new Reservation object instance with the given attribute values
     * @throws RARException in case either the pickupTime is in the past, rentalLength is non-positive, or if the vehicleType, rentalLocation, customer is null
     */
    public Reservation createReservation( Date pickupTime, int rentalLength, VehicleType vehicleType, 
                                          RentalLocation rentalLocation, Customer customer ) throws RARException;

    /**
     * Create a new Reservation object with undefined attribute values.
     * @return the new Reservation object instance
     */
    public Reservation createReservation();

    /**
     * Return an List of Reservation objects satisfying the search criteria given in the modelReservation object.
     * @param modelReservation a model Reservation object specifying the search criteria
     * @return an List of the located Reservation objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<Reservation> findReservation( Reservation modelReservation ) throws RARException;
    
    /**
     * Store a given Reservation object in persistent data store.
     * @param reservation the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeReservation( Reservation reservation ) throws RARException;
    
    /**
     * Delete this Reservation object.
     * @param reservation the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteReservation( Reservation reservation ) throws RARException;

    /**
     * Create a new Rental object, given the set of initial attribute values.
     * @param pickupTime the pickup time of the vehicle
     * @param reservation the reservation for this rental
     * @param vehicle the vehicle being rented
     * @return the new Reservation object instance with the given attribute values
     * @throws RARException in case either the pickupTime is in the past or if the reservation or the vehicle is null
     */
    public Rental createRental( Date pickupTime, Reservation reservation, Vehicle vehicle ) throws RARException;

    /**
     * Create a new Rental object with undefined attribute values.
     * @return the new Rental object instance
     */
    public Rental createRental();

    /**
     * Return a List of Rental objects satisfying the search criteria given in the modelRental object.
     * @param modelRental a model Rental object specifying the search criteria
     * @return an List of the located Rental objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<Rental> findRental( Rental modelRental ) throws RARException;
    
    /**
     * Store a given Rental object in persistent data store.
     * @param rental the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeRental( Rental rental ) throws RARException;
    
    /**
     * Delete this Rental object.
     * @param rental the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteRental( Rental rental ) throws RARException;    
    
    /**
     * Create a new VehicleType object, given the set of initial attribute value.
     * @param name the name of the vehicle type, e.g. "Sedan", "Pickup", etc.
     * @return the new VehicleType object instance with the given attribute value
     * @throws RARException in case the vehicle type name is non-unique
     */
    public VehicleType createVehicleType( String name ) throws RARException;

    /**
     * Create a new VehicleType object with undefined attribute values.
     * @return the new VehicleType object instance
     */
    public VehicleType createVehicleType();

    /**
     * Return a List of VehicleType objects satisfying the search criteria given in the modelVehicleType object.
     * @param modelVehicleType a model VehicleType object specifying the search criteria
     * @return an List of the located VehicleType objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<VehicleType> findVehicleType( VehicleType modelVehicleType ) throws RARException;
    
    /**
     * Store a given VehicleType object in persistent data store.
     * @param vehicleType the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeVehicleType( VehicleType vehicleType ) throws RARException;
    
    /**
     * Delete this VehicleType object.
     * @param vehicleType the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteVehicleType( VehicleType vehicleType ) throws RARException;

    /**
     * Create a new Vehicle object, given the set of initial attribute value.
     * @param make the make of the vehicle
     * @param model the model
     * @param year the year of the vehicle
     * @param registrationTag the registration tag
     * @param mileage the current mileage of the vehicle
     * @param lastServiced the date when the vehicle was last serviced
     * @param vehicleType the type of the created vehicle (cannot be null)
     * @param rentalLocation the rental location of this vehicle (cannot be null)
     * @param vehicleCondition the condition of this vehicle 
     * @param vehicleStatus the status of this vehicle 
     * @return the new Vehicle object instance with the given attribute values
     * @throws RARException in case either the year or mileage are non-positive, or the vehicleType and/or the rentalLocation is null
     */
    public Vehicle createVehicle( String make, String model, int year, String registrationTag, int mileage, Date lastServiced,
                                  VehicleType vehicleType, RentalLocation rentalLocation, VehicleCondition vehicleCondition, 
                                  VehicleStatus vehicleStatus ) throws RARException;

    /**
     * Create a new Vehicle object with undefined attribute values.
     * @return the new Vehicle object instance
     */
    public Vehicle createVehicle();

    /**
     * Return a List of Vehicle objects satisfying the search criteria given in the modelVehicle object.
     * @param modelVehicle a model Vehicle object specifying the search criteria
     * @return an List of the located Vehicle objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<Vehicle> findVehicle( Vehicle modelVehicle ) throws RARException;
    
    /**
     * Store a given Vehicle object in persistent data store.
     * @param vehicle the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeVehicle( Vehicle vehicle ) throws RARException;
    
    /**
     * Delete this Vehicle object.
     * @param vehicle the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteVehicle( Vehicle vehicle ) throws RARException;

    /**
     * Create a new Comment object, given the set of initial attribute value.
     * @param text the text of the comment
     * @param date the date the comment was made
     * @param rental the rental for which the comment is made
     * @return the new Comment object instance with the given attribute values
     * @throws RARException in case either the rental is null
     */
    public Comment createComment( String text, Date date, Rental rental ) throws RARException;

    /**
     * Create a new Comment object with undefined attribute values.
     * @return the new Comment object instance
     */
    public Comment createComment();

    /**
     * Return a List of Comment objects satisfying the search criteria given in the modelComment object.
     * @param modelComment a model Comment object specifying the search criteria
     * @return an List of the located Comment objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<Comment> findComment( Comment modelComment ) throws RARException;
    
    /**
     * Store a given Comment object in persistent data store.
     * @param comment the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeComment( Comment comment ) throws RARException;
    
    /**
     * Delete this Comment object.
     * @param comment the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteComment( Comment comment ) throws RARException;

    /**
     * Create a new HourlyPrice object, given the set of initial attribute values.
     * @param maxHrs the maximum number of hours for this price
     * @param price the price for this hourly range
     * @param vehicleType the vehicle type this hourly price is for
     * @return the new HourlyPrice object instance with the given attribute values
     * @throws RARException in case either maxHrs or price is non-positive or if the vehicleType is null
     */
    public HourlyPrice createHourlyPrice( int maxHrs, int price, VehicleType vehicleType ) throws RARException;

    /**
     * Create a new HourlyPrice object with undefined attribute values.
     * @return the new HourlyPrice object instance
     */
    public HourlyPrice createHourlyPrice();

    /**
     * Return a List of HourlyPrice objects satisfying the search criteria given in the modelHourlyPrice object.
     * @param modelHourlyPrice a model HourlyPrice object specifying the search criteria
     * @return an List of the located HourlyPrice objects
     * @throws RARException in case there is a problem with the retrieval of the requested objects
     */
    public List<HourlyPrice> findHourlyPrice( HourlyPrice modelHourlyPrice ) throws RARException;
    
    /**
     * Store a given HourlyPrice object in persistent data store.
     * @param hourlyPrice the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeHourlyPrice( HourlyPrice hourlyPrice ) throws RARException;
    
    /**
     * Delete this HourlyPrice object.
     * @param hourlyPrice the object to be deleted.
     * @throws RARException in case there is a problem with the deletion of the object
     */
    public void deleteHourlyPrice( HourlyPrice hourlyPrice ) throws RARException;

    /**
     * Create a new RentARideConfig object with undefined attribute values.
     * @return the new RentARideConfig object instance
     */
    public RentARideParams createRentARideParams();
    
    /**
     * Return the RentARideConfig object.  The RentARideConfig class is a singleton class,
     * so only one object will exist.
     * @return the RentARideConfig object
     */
    public RentARideParams findRentARideParams();
    
    /**
     * Store a given RentARideConfig object in persistent data store.
     * @param rentARideParams the object to be persisted
     * @throws RARException in case there was an error while persisting the object
     */
    public void storeRentARideParams( RentARideParams rentARideParams ) throws RARException;

}
