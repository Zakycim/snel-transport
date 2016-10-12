package nl.cimsolutions.snel_transport.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

public class OrderStatus {
	

	@TableGenerator(
            name = "generator",
            allocationSize = 1,
            initialValue = 1)
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
   // @SequenceGenerator(name = "product_gen", allocationSize = 1)
    private Long StatusID;
	private String StatusName;
	
	public OrderStatus(){
		
	}
	public OrderStatus(Long statusID, String statusName) {
		super();
		StatusID = statusID;
		StatusName = statusName;
	}
	public Long getStatusID() {
		return StatusID;
	}
	public void setStatusID(Long statusID) {
		StatusID = statusID;
	}
	public String getStatusName() {
		return StatusName;
	}
	public void setStatusName(String statusName) {
		StatusName = statusName;
	}
    
    
}
