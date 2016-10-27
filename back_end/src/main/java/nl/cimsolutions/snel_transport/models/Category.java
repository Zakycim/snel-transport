package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;


@Entity
public class Category implements Serializable {

    @TableGenerator(
            name = "ProductGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator="ProductGenerator")
    @Column(name="categoryId")
    private Long categoryId;

    private String name;
	
    public Category(){
    }
    
    public Category(Long categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
    
}
