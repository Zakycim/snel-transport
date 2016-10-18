package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.List;

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
@Table(name="Orderlist")
public class OrderList implements Serializable{

	private static final long serialVersionUID = 1L;
	@TableGenerator(
            name = "OrderListGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
        generator = "OrderListGenerator")
	@Column(name="id")
    private Long listid;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id")
	private List<Order> orders;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="truckid")
	private List<Truck> trucks;
	
	public OrderList() {
		
	}

	public OrderList(Long listid, List<Order> orders, List<Truck> trucks) {
		super();
		this.listid = listid;
		this.orders = orders;
		this.trucks = trucks;
	}

	public Long getListid() {
		return listid;
	}

	public void setListid(Long listid) {
		this.listid = listid;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Truck> getTrucks() {
		return trucks;
	}

	public void setTrucks(List<Truck> trucks) {
		this.trucks = trucks;
	}
	

}
