package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.services.OrderListFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;

@Entity
@Table(name="\"OrderList\"")

public class OrderList implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @TableGenerator(
            name = "generator",
            allocationSize = 1,
            initialValue = 1)
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    private Long OrderListID;
    @NotNull
    private Long TruckID;
    @NotNull
    private Long OrderID;
	private Long OrderofOrder;
	@OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OrderID")
	private Order order;
    
//	@OneToOne
//	@JoinColumn(name="TruckID")
//	private Truck truck;
//	
	public OrderList(){
		
	}
	

	public Long getOrderListID() {
		return OrderListID;
	}

	public void setOrderListID(Long orderListID) {
		OrderListID = orderListID;
	}

	public Long getTruckID() {
		return TruckID;
	}

	public void setTruckID(Long truckID) {
		TruckID = truckID;
	}

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

	public Long getOrderofOrder() {
		return OrderofOrder;
	}

	public void setOrderofOrder(Long orderofOrder) {
		OrderofOrder = orderofOrder;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
