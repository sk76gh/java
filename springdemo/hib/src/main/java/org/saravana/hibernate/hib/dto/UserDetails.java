package org.saravana.hibernate.hib.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="USER_DETAILS")
public class UserDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;
	
	private String userName;
	
	@Temporal (TemporalType.DATE)
	private Date joinedDate;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="USER_ADDRESS_LIST", joinColumns=@JoinColumn(name="USER_ID"))
	private Collection<Address> addressList=new ArrayList<Address>();
	
	@Lob
	private String description;
	
	@Transient
	private String dontAddtoDB;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="VEHICLE_ID")
	private UserVehicle vehicle;
	
	/*user a join table
	 * @OneToMany()
	@JoinTable(name="USER_VEHICLE", 
	joinColumns=@JoinColumn(name="USER_ID"),
	inverseJoinColumns=@JoinColumn(name="VEHICLE_ID"))
	private Collection<UserVehicle> vehicles= new ArrayList<UserVehicle>();
*/
	//does not use a join table
	@OneToMany(mappedBy="user", cascade=CascadeType.PERSIST)
	//@JoinTable(name="USER_VEHICLE", 
	//joinColumns=@JoinColumn(name="USER_ID"),
	//inverseJoinColumns=@JoinColumn(name="VEHICLE_ID"))
	private Collection<UserVehicle> vehicles= new ArrayList<UserVehicle>();
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String toString(){
		return String.valueOf(getUserId()) +getDescription()+getUserName();
		
	}
	public Collection<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(Collection<Address> addressList) {
		this.addressList = addressList;
	}
	public UserVehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(UserVehicle vehicle) {
		this.vehicle = vehicle;
		vehicle.setUser(this);
	}
	public Collection<UserVehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Collection<UserVehicle> vehicles) {
		this.vehicles = vehicles;
		for (UserVehicle vehicle: vehicles){
			vehicle.setUser(this);
		}
	}



}
