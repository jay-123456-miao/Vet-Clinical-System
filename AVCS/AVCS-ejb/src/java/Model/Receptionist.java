/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ASUS
 */
@Entity
@NamedQueries
({
    @NamedQuery(name="Receptionist.searchNotApproved", query="SELECT r FROM Receptionist r WHERE r.status = 'Unapproved'"),
    @NamedQuery(name="Receptionist.searchByUsername", query="SELECT r FROM Receptionist r WHERE r.username = :username"),
    @NamedQuery(name="Receptionist.approveRegistration", query="UPDATE Receptionist r SET r.status = :status WHERE r.id = :id"),
})
public class Receptionist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private int password;
    private String email;
    private String gender;
    private String usertype;
    private Long contactnumber;
    private int age;
    private String status;

    public Receptionist() {
    }

    public Receptionist(String username, int password, String email, String gender, String usertype, Long contactnumber, int age, String status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.usertype = usertype;
        this.contactnumber = contactnumber;
        this.age = age;
        this.status = status;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Long getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(Long contactnumber) {
        this.contactnumber = contactnumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receptionist)) {
            return false;
        }
        Receptionist other = (Receptionist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Receptionist[ id=" + id + " ]";
    }
    
}
