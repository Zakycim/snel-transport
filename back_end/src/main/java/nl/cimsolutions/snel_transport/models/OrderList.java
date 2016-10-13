package nl.cimsolutions.snel_transport.models;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="OrderList")
@Access(AccessType.FIELD)
public class OrderList {
	
	@TableGenerator(
            name = "ProductGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator="ProductGenerator")
    @Column(name="id")
	private Long OrderListId;
    @Column(name="truckid")
	private Long TruckID;
    @Column(name="orderid")
	private Long OrderID;
    @Column(name="orderoforder")
	private Long OrderofOrder;
	@OneToMany
    @JoinColumn(name="orderid_fk")
	private List<Order> order;
	
	public OrderList(){
		
	}
	
	public OrderList(Long orderListId, Long truckID, Long orderID, Long orderofOrder, List<Order> order) {
		super();
		OrderListId = orderListId;
		TruckID = truckID;
		OrderID = orderID;
		OrderofOrder = orderofOrder;
		this.order = order;
	}

	public Long getOrderListId() {
		return OrderListId;
	}

	public void setOrderListId(Long orderListId) {
		OrderListId = orderListId;
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

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (OrderListId != null ? OrderListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderList)) {
            return false;
        }
        OrderList other = (OrderList) object;
        if ((this.OrderListId == null && other.OrderListId != null) || (this.OrderListId != null && !this.OrderListId.equals(other.OrderListId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrderList[ id=" + OrderListId + " ]";
    }
	

}
