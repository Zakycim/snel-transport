package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(
            name = "RouteGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
        generator = "RouteGenerator")
    private Long id;
    private int distance;
    private String duration;
    @ManyToOne
    @JoinColumn(name="customerA")
    private Customer customerA;
    @ManyToOne
    @JoinColumn(name="customerB")
    private Customer customerB;
    @Column(name = "truckId")
    private Long truckId;
    
    public Route() {}

    public Route(Long id, int distance, String duration, Customer customerA, Customer customerB, Long truckId) {
        super();
        this.id = id;
        this.distance = distance;
        this.duration = duration;
        this.customerA = customerA;
        this.customerB = customerB;
        this.truckId = truckId;
    }

    public Long getTruckId() {
        return truckId;
    }



    public void setTruckId(Long truckId) {
        this.truckId = truckId;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Customer getCustomerA() {
        return customerA;
    }
    public void setCustomerA(Customer customerA) {
        this.customerA = customerA;
    }
    public Customer getCustomerB() {
        return customerB;
    }
    public void setCustomerB(Customer customerB) {
        this.customerB = customerB;
    }
    
}
