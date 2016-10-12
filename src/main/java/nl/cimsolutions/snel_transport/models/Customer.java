package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="\"Customer\"")
public class Customer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
    @SequenceGenerator(name = "order_gen", allocationSize = 1)
    private Long id;
    private String name;
    private Double price;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    private Long customerId;
    private Long status;
    @OneToMany
    @JoinColumn(name="orderId")
    private List<OrderLine> orderLines;
    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
    
}
