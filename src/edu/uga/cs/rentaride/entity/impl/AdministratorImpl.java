package edu.uga.cs.rentaride.entity.impl;

import java.util.Date;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.persistence.impl.PersistableImpl;

public class AdministratorImpl implements Administrator {

	//Administrator (User) attributes
    private String           firstName;
    private String           lastName;
    private String           userName;
    private String           password;
    private String           email;
    private String           address;
    private Date             createDate;
    private long		     id;
    private UserStatus 		 status;

    public AdministratorImpl()
    {
        super();
        this.firstName = null;
        this.lastName = null;
        this.userName = null;
        this.password = null;
        this.email = null;
        this.address = null;
        this.createDate = null;
        this.id = -1;
        status = null;
    }
    
    public AdministratorImpl(String firstName,
		                     String lastName,
		                     String userName,
		                     String password, 
		                     String email,
		                     String address, 
		                     Date createdDate, 
		                     //long id,
		                     UserStatus status)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.createDate = createdDate;
        //this.id = id;
        this.status = status;
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
		return this.userName;
	}

	@Override
	public void setUserName(String userName) throws RARException {
        this.userName = userName;
	}

	@Override
	public String getEmail() {
		return this.email;
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

}
