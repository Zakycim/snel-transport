package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.core.Response;

import nl.cimsolutions.snel_transport.services.CustomerFacade;
import nl.cimsolutions.snel_transport.services.OrdersFacade;
import nl.cimsolutions.snel_transport.services.ProductFacade;
import nl.cimsolutions.snel_transport.services.StatusFacade;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "OrderGenerator", allocationSize = 1, initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "OrderGenerator")
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @OneToOne
    @JoinColumn(name = "statusid")
    private Status status;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderId")
    private List<OrderLine> orderLines;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order() {

    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Order(Long id, Date orderDate, Date deliveryDate, Status status, List<OrderLine> orderLines,
            Customer customer) {
        super();
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.orderLines = orderLines;
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are
        // not set
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Order[ id=" + id + " ]";
    }

    public Order completeFlow(Order data) throws Exception {
        Order order = new Order();
        CustomerFacade customerFacade = new CustomerFacade();
        StatusFacade statusFacade = new StatusFacade();
        Date orderDate = new Date();
        OrdersFacade orderFacade = new OrdersFacade();
        Order newlyOrder = new Order();

        if (data.getCustomer().getId() == null) {
            throw new Exception("customer ID is required");
        }

        Customer customer = customerFacade.find(data.getCustomer().getId());
        if (customer == null) {
            throw new Exception("customerID wasn't found");
        }

        Status status = statusFacade.find(1L);
        order.setCustomer(customer);
        order.setOrderDate(orderDate);
        order.setStatus(status);

        if (data.getOrderLines() == null) {
            throw new Exception("Order lines are required");
        }

        List<OrderLine> orderLines = new ArrayList<OrderLine>();
        Product product = new Product();
        ProductFacade productFacade = new ProductFacade();

        for (int i = 0; i < data.getOrderLines().size(); i++) {
            OrderLine orderLine = new OrderLine();
            product = productFacade.find(data.getOrderLines().get(i).getProduct().getId());
            if (product == null) {
                throw new Exception("product ID wasn't found");
            }

            orderLine.setProduct(product);
            orderLine.setAmount(data.getOrderLines().get(i).getAmount());
            orderLines.add(orderLine);
        }
        
        //Complete the flow by adding the order in the database
        order.setOrderLines(orderLines);
        newlyOrder = orderFacade.create(order);
        return newlyOrder;
    }
}
