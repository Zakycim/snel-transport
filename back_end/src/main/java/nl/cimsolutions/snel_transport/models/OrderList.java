package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import nl.cimsolutions.snel_transport.services.OrderListFacade;

@Entity
//@NamedQuery(query = "Select e from Orderlist e", name = "find Orderlist by id")
public class OrderList implements Serializable{

	private static final long serialVersionUID = 1L;
	@TableGenerator(
            name = "OrderListGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
        generator = "OrderListGenerator")
	@Column(name = "id")
    private Long id;
	@Column(name = "orderoforder")
	private Long orderOfOrder;
	@Column(name = "truckId")
    private Long truckId;
	@OneToOne
	@JoinColumn(name="orderid")
	private Orders order;//List<Orders> order;
	
	
	 @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (id != null ? id.hashCode() : 0);
	        return hash;
	    }
	 
	public OrderList() {
		
	}

	public OrderList(Long id, Long truckId, Long orderoforder, Orders order) {
		super();
		this.id = id;
		this.truckId = truckId;
		this.order = order;
		this.orderOfOrder = orderoforder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getOrderOfOrder() {
        return orderOfOrder;
    }

    public void setOrderOfOrder(Long orderOfOrder) {
        this.orderOfOrder = orderOfOrder;
    }

    public Long getTruckId() {
		return truckId;
	}

	public void setTruckId(Long truckId) {
		this.truckId = truckId;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders orders) {
		this.order = orders;
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderList)) {
            return false;
        }
        OrderList other = (OrderList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.OrderList[ id=" + id + " ]";
    }
    

}
