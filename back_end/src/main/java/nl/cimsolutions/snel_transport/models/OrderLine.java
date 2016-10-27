package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity 
public class OrderLine implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @TableGenerator(
            name = "OrderLineGenerator",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,
        generator="OrderLineGenerator")
    private Long id;
    
    @Column(name = "orderId")
    private Long orderId;
    
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    
    private int amount;
    
   

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

    @Override
    public String toString() {
        return "model.OrderLine[ id=" + id + " ]";
    }
    
}