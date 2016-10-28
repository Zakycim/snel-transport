package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
public class OrderList implements Serializable{

	private static final long serialVersionUID = 1L;
	@TableGenerator(
            name = "OrderListGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
        generator = "OrderListGenerator")
    private Long id;
//	@Column(name = "orderId")
//  private Long orderId;
	 @Column(name = "truckId")
	    private Long truckId;
//	@OneToMany
//	@JoinColumn(name="id")
//	private List<Orders> order;

	@OneToOne
	@JoinColumn(name = "orderId")
    private Orders order;
	
	public OrderList() {
		
	}

    public OrderList(Long id, Long truck, Orders order) {
        super();
        this.id = id;
        this.truckId = truck;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTruck() {
		return truckId;
	}

	public void setTruck(Long truck) {
		this.truckId = truck;
	}

	public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
