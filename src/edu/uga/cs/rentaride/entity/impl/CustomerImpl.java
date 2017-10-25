package edu.uga.cs.rentaride.entity.impl;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.PersistableImpl;

public class CustomerImpl implements Customer {

	//User attributes
	private String            firstName;
    private String            lastName;
    private String            userName;
    private String            password;
    private String            email;
    private String            address;
    private Date              createDate;
    private UserStatus 		  status;
    private long		      id;	 

    //Customer attributes
	private Date 			  memberUntil;
	private String 			  licState;
	private String 			  licNumber;
	private String 			  ccNumber;
	private Date 			  ccExpiration;
	
	//References
	private List<Reservation> reservations;
	private List<Comment>	  comments;
	private List<Rental>	  rentals;
	
	public CustomerImpl() {
		super();
		
		this.firstName = null;
        this.lastName = null;
        this.userName = null;
        this.password = null;
        this.email = null;
        this.address = null;
        this.createDate = null;
        this.id = -1;
		
		this.memberUntil = null;
		this.licState = null;
		this.licNumber = null;
		this.ccNumber = null;
		this.ccExpiration = null;
		this.status = null;
		
		reservations = null;
		comments = null;
		rentals = null;
	}

	public CustomerImpl(String firstName,
						String lastName,
						String userName,
						String password,
						String email,
						String address,
						Date createDate,
						//long id,
						Date memberUntil,
						String licState,
						String licNumber,
						String ccNumber,
						Date ccExpiration,
						UserStatus status)
						//List<Reservation> reservations,
						//List<Comment> comments,
						//List<Rental> rentals)
	{
		super();
		
		this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.createDate = createDate;
        //this.id = id;
		
		this.memberUntil = memberUntil;
		this.licState = licState;
		this.licNumber = licNumber;
		this.ccNumber = ccNumber;
		this.ccExpiration = ccExpiration;
		this.status = status;
		
		//this.reservations = reservations;
		//this.comments = comments;
		//this.rentals = rentals;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
		this.userName = userName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Date getCreatedDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public UserStatus getUserStatus() {
		return status;
	}

	@Override
	public void setUserStatus(UserStatus userStatus) {
		this.status = userStatus;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean isPersistent() {
		if (id != -1) return true;
		else return false;
	}

	@Override
	public Date getMemberUntil() {
		return memberUntil;
	}

	@Override
	public void setMemberUntil(Date memberUntil) throws RARException {
		this.memberUntil = memberUntil;
	}

	@Override
	public String getLicenseState() {
		return licState;
	}

	@Override
	public void setLicenseState(String state) {
		this.licState = licState;
	}

	@Override
	public String getLicenseNumber() {
		return licNumber;
	}

	@Override
	public void setLicenseNumber(String licenseNumber) {
		this.licNumber = licenseNumber;
	}

	@Override
	public String getCreditCardNumber() {
		return ccNumber;
	}

	@Override
	public void setCreditCardNumber(String cardNumber) {
		this.ccNumber = cardNumber;
	}

	@Override
	public Date getCreditCardExpiration() {
		return ccExpiration;
	}

	@Override
	public void setCreditCardExpiration(Date cardExpiration) {
		this.ccExpiration = cardExpiration;
	}

	@Override
	public List<Reservation> getReservations() {
		return reservations;
	}

	@Override
	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public List<Rental> getRentals() {
		return rentals;
	}

}