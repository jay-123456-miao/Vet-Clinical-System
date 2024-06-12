/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    @NamedQuery(name="Appointment.searchUnfinishedAppointments", query="SELECT a FROM Appointment a WHERE a.vet_username = :vet_username AND a.status = 'Unfinished'"),
    @NamedQuery(name="Appointment.searchFinishedAppointments", query="SELECT a FROM Appointment a WHERE a.vet_username = :vet_username AND a.status = 'Finished'")
})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vet_username;
    private String customer_username;
    private String pet_username;
    private LocalDateTime appointment_time;
    private String diagnosis;
    private String prognosis;
    private String status;
    private String gender;
    private String email;
    private Long contact_number;
    private int age;
    private String species;

    public Appointment() {
    }

    public Appointment(String vet_username, String customer_username, String pet_username, LocalDateTime appointment_time, String diagnosis, String prognosis, String status, String gender, String email, Long contact_number, int age, String species) {
        this.vet_username = vet_username;
        this.customer_username = customer_username;
        this.pet_username = pet_username;
        this.appointment_time = appointment_time;
        this.diagnosis = diagnosis;
        this.prognosis = prognosis;
        this.status = status;
        this.gender = gender;
        this.email = email;
        this.contact_number = contact_number;
        this.age = age;
        this.species = species;
    }

    public String getVet_username() {
        return vet_username;
    }

    public void setVet_username(String vet_username) {
        this.vet_username = vet_username;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getPet_username() {
        return pet_username;
    }

    public void setPet_username(String pet_username) {
        this.pet_username = pet_username;
    }

    public LocalDateTime getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(LocalDateTime appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(String prognosis) {
        this.prognosis = prognosis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContact_number() {
        return contact_number;
    }

    public void setContact_number(Long contact_number) {
        this.contact_number = contact_number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
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
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Appointment[ id=" + id + " ]";
    }
    
}
