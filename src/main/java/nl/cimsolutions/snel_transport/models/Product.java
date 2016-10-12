package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="\"Product\"")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
            name = "product_gen",
            allocationSize = 1,
            initialValue = 1)
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "product_gen")
   // @SequenceGenerator(name = "product_gen", allocationSize = 1)
    private Long ProductID;
    @NotNull
	private String Name;
	private String Code;
	private double Price;
	private Long CategoryID;
   // @OneToMany
   // @JoinColumn(name="productId")
    //private List<OrderLine> orderLines;
	
	public Product(){
		
	}
	
	public Product(Long productID, String name, String code, double price, Long categoryID) {
		super();
		ProductID = productID;
		Name = name;
		Code = code;
		Price = price;
		CategoryID = categoryID;
	}
	
	public Long getProductID() {
		return ProductID;
	}
	public void setProductID(Long productID) {
		ProductID = productID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public Long getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(Long categoryID) {
		CategoryID = categoryID;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (ProductID != null ? ProductID.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Product)) {
			return false;
		}
		Product other = (Product) object;
		if ((this.ProductID == null && other.ProductID != null) || (this.ProductID != null && !this.ProductID.equals(other.ProductID))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "model.Product[ id=" + ProductID + " ]";
	}

}

