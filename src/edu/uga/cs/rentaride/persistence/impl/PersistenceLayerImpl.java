package edu.uga.cs.rentaride.persistence.impl;

import java.util.List;

import com.mysql.jdbc.Connection;

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
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;

public class PersistenceLayerImpl implements PersistenceLayer {

	private AdministratorManager 	 administratorManager = null;
	private CommentManager 			 commentManager = null;
	private CustomerManager 	     customerManager = null;
	private HourlyPriceManager   	 hourlyPriceManager = null;
	private RentalLocationManager 	 rentalLocationManager = null;
	private RentalManager			 rentalManager = null;
	private ReservationManager		 reservationManager = null;
	private VehicleManager			 vehicleManager = null;
	private VehicleTypeManager		 vehicleTypeManager = null;
	
	public PersistenceLayerImpl(Connection conn, ObjectLayer objectLayer) {
		administratorManager = new AdministratorManager(conn, objectLayer);
		commentManager = new CommentManager(conn, objectLayer);
		customerManager = new CustomerManager(conn, objectLayer); 
		hourlyPriceManager = new HourlyPriceManager(conn, objectLayer); 
		rentalLocationManager = new RentalLocationManager(conn, objectLayer); 
		rentalManager = new RentalManager(conn, objectLayer);
		reservationManager = new ReservationManager(conn, objectLayer); 
		vehicleManager = new VehicleManager(conn, objectLayer);
		vehicleTypeManager = new VehicleTypeManager(conn, objectLayer);
	}
	
	@Override
	public List<Administrator> restoreAdministrator(Administrator modelAdministrator) throws RARException {
		return administratorManager.restore(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		administratorManager.save(administrator);
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		administratorManager.delete(administrator);
	}

	@Override
	public List<Customer> restoreCustomer(Customer modelCustomer) throws RARException {
		return restoreCustomer(modelCustomer);
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		customerManager.save(customer);
	}

	@Override
	public List<RentalLocation> restoreRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return rentalLocationManager.restore(modelRentalLocation);
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		rentalLocationManager.save(rentalLocation);
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		rentalLocationManager.delete(rentalLocation);
	}

	@Override
	public List<Reservation> restoreReservation(Reservation modelReservation) throws RARException {
		return reservationManager.restore(modelReservation);
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		reservationManager.save(reservation);
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		reservationManager.delete(reservation);
	}

	@Override
	public List<Rental> restoreRental(Rental modelRental) throws RARException {
		return rentalManager.restore(modelRental);
	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		rentalManager.save(rental);
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		rentalManager.delete(rental);
	}

	@Override
	public List<VehicleType> restoreVehicleType(VehicleType modelVehicleType) throws RARException {
		return vehicleTypeManager.restore(modelVehicleType);
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		vehicleTypeManager.save(vehicleType);
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		vehicleTypeManager.delete(vehicleType);
	}

	@Override
	public List<Vehicle> restoreVehicle(Vehicle modelVehicle) throws RARException {
		return vehicleManager.restore(modelVehicle);
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		vehicleManager.save(vehicle);
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		vehicleManager.delete(vehicle);
	}

	@Override
	public List<Comment> restoreComment(Comment modelComment) throws RARException {
		return commentManager.restore(modelComment);
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		commentManager.save(comment);
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		commentManager.delete(comment);
	}

	@Override
	public List<HourlyPrice> restoreHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return hourlyPriceManager.restore(modelHourlyPrice);
	}

	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		hourlyPriceManager.save(hourlyPrice);
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		hourlyPriceManager.restore(hourlyPrice);
	}

	//NEED TO DO THIS
	@Override
	public RentARideParams restoreRentARideConfig() throws RARException {
		return null;
	}

	//NEED TO DO THIS
	@Override
	public void storeRentARideConfig(RentARideParams rentARideConfig) throws RARException {
		return;
	}

	@Override
	public void storeCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		reservationManager.save(customer, reservation);
	}

	@Override
	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		return reservationManager.restoreCustomerReservation(reservation);
	}

	@Override
	public List<Reservation> restoreCustomerReservation(Customer customer) throws RARException {
		return reservationManager.restoreCustomerReservation(customer);
	}

	@Override
	public void deleteCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		reservationManager.deleteCustomerReservation(customer, reservation);
	}

	@Override
	public void storeReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation) throws RARException {
		
	}

	@Override
	public RentalLocation restoreReservationRentalLocation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> restoreReservationRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType restoreReservationVehicleType(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> restoreReservationVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RentalLocation restoreVehicleRentalLocation(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> restoreVehicleRentalLocation(RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType restoreVehicleVehicleType(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vehicle> restoreVehicleVehicleType(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VehicleType restoreVehicleTypeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HourlyPrice> restoreVehicleTypeHourlyPrice(VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeRentalComment(Rental rental, Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rental restoreRentalComment(Comment comment) throws RARException {
		return null; 
		//return commentManager.restoreRentalComment(comment);
	}

	@Override
	public List<Comment> restoreRentalComment(Rental rental) throws RARException {
		return null;
	}

	@Override
	public void deleteRentalComment(Rental rental, Comment comment) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeRentalReservation(Rental rental, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rental restoreRentalReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation restoreRentalReservation(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRentalReservation(Rental rental, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeVehicleRental(Vehicle vehicle, Rental rental) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Rental> restoreVehicleRental(Vehicle vehicle) throws RARException {
		return VehicleManager.restoreVehicleRental(vehicle);
	}

	@Override
	public Vehicle restoreVehicleRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVehicleRental(Vehicle vehicle, Rental rental) throws RARException {
		rentalManager.delete(vehicle, rental);
	}

}
