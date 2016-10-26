package nl.cimsolutions.snel_transport.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Z.Huraibi
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableGenerator(
            name = "CustomerGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator="CustomerGenerator")
    private Long id;
    private String name;
    private String adres;
    @Column(name="Number")
    private String streetNumber;
    @Column(name="PostalCode")
    private String postalCode;
    @Column(name="City")
    private String city;
    @Column(name="Tel")
    private String tel;
    @Column(name="Fax")
    private String fax;
    @OneToMany(mappedBy = "customer", targetEntity = Orders.class)
    private List<Orders> orders;
    
    @OneToMany(mappedBy = "customerA")
    private List<Route> routes;
    
    @OneToMany(mappedBy = "customerB")
    private List<Route> routes2;

    public Customer() {
        // TODO Auto-generated constructor stub
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "model.OrderLine[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public String getAdres() {
        return adres;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    
}