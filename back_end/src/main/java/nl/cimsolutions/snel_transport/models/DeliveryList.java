package nl.cimsolutions.snel_transport.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
public class DeliveryList {
    @Id
    @TableGenerator(
            name = "DeliveryListGenerator",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,
        generator="DeliveryListGenerator")
    private Long id;
    
    private Long orderOfOrder;
    
    @ManyToOne
    @JoinColumn(name="truckId")
    private Truck truck;
    
    @OneToOne
    @JoinColumn(name="orderId")
    private Order order;
    
    public DeliveryList() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public Long getOrderOfOrder() {
        return orderOfOrder;
    }

    public Truck getTruck() {
        return truck;
    }

    public Order getOrder() {
        return order;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderOfOrder(Long orderOfOrder) {
        this.orderOfOrder = orderOfOrder;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
