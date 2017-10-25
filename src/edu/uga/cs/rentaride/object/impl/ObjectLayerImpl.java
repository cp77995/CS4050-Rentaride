package edu.uga.cs.rentaride.object.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class ObjectLayerImpl implements ObjectLayer {

	PersistenceLayer persistence = null;
    
    public ObjectLayerImpl()
    {
        this.persistence = null;
        System.out.println("ObjectLayerImpl.ObjectLayerImpl(): initialized");
    }
    
    public ObjectLayerImpl( PersistenceLayerImpl persistence)
    {
        this.persistence = persistence;
        System.out.println("ObjectLayerImpl.ObjectLayerImpl(persistence): initialized");
    }
    
    public void setPersistence(PersistenceLayer persistence) {
    	this.persistence = persistence;
    	System.out.println("ObjectLayerImpl.setPersistence(persistence): initialized");
    
    }
	
	
	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
		return new AdministratorImpl(firstName, lastName, userName, password, email, address, createDate, UserStatus.ACTIVE);
	}

	@Override
	public Administrator createAdministrator() {
		return new AdministratorImpl(null, null, null, null, null, null, null, UserStatus.ACTIVE);
	}

	@Override
	public List<Administrator> findAdministrator(Administrator modelAdministrator) throws RARException {
        return persistence.restoreAdministrator(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		persistence.storeAdministrator(administrator);
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		persistence.deleteAdministrator(administrator);
	}

	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration) throws RARException {
		return new CustomerImpl(firstName, lastName, userName, password, email, address, createDate, membershipExpiration, licenseState, licenseNumber, cardNumber, cardExpiration, UserStatus.ACTIVE);
	}

	@Override
	public Customer createCustomer() {
		return new CustomerImpl();
	}

	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		return persistence.restoreCustomer( modelCustomer );
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		persistence.storeCustomer(customer);
	}

	@Override
	public RentalLocation createRentalLocation(String name, String address, int capacity) throws RARException {
		return new RentalLocationImpl(name, address, capacity);
	}

	@Override
	public RentalLocation createRentalLocation() {
		return new RentalLocationImpl();
	}

	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return persistence.restoreRentalLocation(modelRentalLocation);
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.storeRentalLocation(rentalLocation);
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.deleteRentalLocation(rentalLocation);
	}

	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType,
			RentalLocation rentalLocation, Customer customer) throws RARException {
		return new ReservationImpl(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
	}

	@Override
	public Reservation createReservation() {
		return new ReservationImpl();
	}

	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		return persistence.restoreReservation(modelReservation);
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		persistence.storeReservation(reservation);
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		persistence.deleteReservation(reservation);
	}

	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		return new RentalImpl(pickupTime, reservation, vehicle);
	}

	@Override
	public Rental createRental() {
		return new RentalImpl(null, null, null);
	}

	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		return persistence.restoreRental(modelRental);
	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		persistence.storeRental(rental);
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		persistence.deleteRental(rental);
	}

	@Override
	public VehicleType createVehicleType(String name) throws RARException {
		return new VehicleTypeImpl(name);
	}

	@Override
	public VehicleType createVehicleType() {
		return new VehicleTypeImpl();
	}

	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		return persistence.restoreVehicleType(modelVehicleType);
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		persistence.storeVehicleType(vehicleType);
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		persistence.deleteVehicleType(vehicleType);
	}

	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		return new VehicleImpl(make, model, year, registrationTag, mileage, lastServiced, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
	}

	@Override
	public Vehicle createVehicle() {
		return new VehicleImpl(null, null, -1, null, -1, null, null, null, null, null);
	}

	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		return persistence.restoreVehicle(modelVehicle);
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		persistence.storeVehicle(vehicle);
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		persistence.deleteVehicle(vehicle);
	}

	@Override
	public Comment createComment( String text, Date date, Rental rental ) throws RARException {
		return new CommentImpl(text, date, rental);
	}

	@Override
	public Comment createComment() {
		return new CommentImpl(null, null, null);
	}

	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {
		return persistence.restoreComment(modelComment);
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		persistence.storeComment(comment);
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		persistence.deleteComment(comment);
	}

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		return new HourlyPriceImpl(maxHrs, price, vehicleType);
	}

	@Override
	public HourlyPrice createHourlyPrice() {
		return new HourlyPriceImpl();
	}

	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return persistence.restoreHourlyPrice(modelHourlyPrice);
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.storeHourlyPrice(hourlyPrice);
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.deleteHourlyPrice(hourlyPrice);
	}

	@Override
	public RentARideParams createRentARideParams() {
		return null; //Not sure about this one
	}

	@Override
	public RentARideParams findRentARideParams() {
		return null; //Not sure about this one
	}

	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		persistence.storeRentARideConfig(rentARideParams); //Somehow called RentARideConfig.
	}

}
