package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

@Entity
public class Status implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @TableGenerator(
            name = "StatusGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
        generator = "StatusGenerator")
    private Long id;
    private String name;
    @OneToOne(mappedBy="status")
    private Orders order;

    public Status() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
