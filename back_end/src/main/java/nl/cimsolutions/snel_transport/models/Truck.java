package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "Truck")
@Access(AccessType.FIELD)
public class Truck implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "TruckGenerator", allocationSize = 1, initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TruckGenerator")
    @Column(name = "id")
    private Long id;
    @Column(name = "licenseplate")
    private String LicensePlate;
    @Column(name = "available")
    private boolean Available;
//   
//    @OneToMany(mappedBy="truck", targetEntity = OrderList.class )
//    @JoinColumn(name="orderList")
//    private List<OrderList> orderList;
    
    @OneToMany( cascade = CascadeType.PERSIST)
    @JoinColumn(name="TruckId")
    private List<OrderList> orderList;

    public Truck() {

    }

    public Truck(Long id, String licensePlate, boolean available,List<OrderList> orderList) {
        this.id = id;
        this.LicensePlate = licensePlate;
        this.Available = available;
        this.orderList = orderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<OrderList> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderList> orderList) {
		this.orderList = orderList;
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
