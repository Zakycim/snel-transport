package nl.cimsolutions.snel_transport.models;

public class Product{

	private Long ProductID;
	private String Name;
	private String Code;
	private double Price;
	private Long CategoryID;
	
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
		if (!(object instanceof Order)) {
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

