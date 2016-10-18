package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="Truck")
@Access(AccessType.FIELD)
public class Truck implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableGenerator(
            name = "TruckGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
        generator = "TruckGenerator")
	@Column(name="truckid")
	private Long id;
	@Column(name="licenseplate")
	private String LicensePlate;
	@Column(name="available")
	private boolean Available;
	
	public Truck() {
		
	}
	
	public Truck(Long truckId, String licensePlate, boolean available){
		id = truckId;
		LicensePlate = licensePlate;
		Available = available;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicensePlate() {
		return LicensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		LicensePlate = licensePlate;
	}

	public boolean isAvailable() {
		return Available;
	}

	public void setAvailable(boolean available) {
		Available = available;
	}
	
	
}
