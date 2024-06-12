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

/**
 *
 * @author ASUS
 */
@Entity
public class WorkingRotation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startDate;
    private String monVet1;
    private String monVet2;
    private String monVet3;
    private String tueVet1;
    private String tueVet2;
    private String tueVet3;
    private String wedVet1;
    private String wedVet2;
    private String wedVet3;
    private String thursVet1;
    private String thursVet2;
    private String thursVet3;
    private String friVet1;
    private String friVet2;
    private String friVet3;
    private String satVet1;
    private String satVet2;
    private String satVet3;
    private String sunVet1;
    private String sunVet2;
    private String sunVet3;

    public WorkingRotation() {
    }

    public WorkingRotation(LocalDateTime startDate, String monVet1, String monVet2, String monVet3, String tueVet1, String tueVet2, String tueVet3, String wedVet1, String wedVet2, String wedVet3, String thursVet1, String thursVet2, String thursVet3, String friVet1, String friVet2, String friVet3, String satVet1, String satVet2, String satVet3, String sunVet1, String sunVet2, String sunVet3) {
        this.startDate = startDate;
        this.monVet1 = monVet1;
        this.monVet2 = monVet2;
        this.monVet3 = monVet3;
        this.tueVet1 = tueVet1;
        this.tueVet2 = tueVet2;
        this.tueVet3 = tueVet3;
        this.wedVet1 = wedVet1;
        this.wedVet2 = wedVet2;
        this.wedVet3 = wedVet3;
        this.thursVet1 = thursVet1;
        this.thursVet2 = thursVet2;
        this.thursVet3 = thursVet3;
        this.friVet1 = friVet1;
        this.friVet2 = friVet2;
        this.friVet3 = friVet3;
        this.satVet1 = satVet1;
        this.satVet2 = satVet2;
        this.satVet3 = satVet3;
        this.sunVet1 = sunVet1;
        this.sunVet2 = sunVet2;
        this.sunVet3 = sunVet3;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getMonVet1() {
        return monVet1;
    }

    public void setMonVet1(String monVet1) {
        this.monVet1 = monVet1;
    }

    public String getMonVet2() {
        return monVet2;
    }

    public void setMonVet2(String monVet2) {
        this.monVet2 = monVet2;
    }

    public String getMonVet3() {
        return monVet3;
    }

    public void setMonVet3(String monVet3) {
        this.monVet3 = monVet3;
    }

    public String getTueVet1() {
        return tueVet1;
    }

    public void setTueVet1(String tueVet1) {
        this.tueVet1 = tueVet1;
    }

    public String getTueVet2() {
        return tueVet2;
    }

    public void setTueVet2(String tueVet2) {
        this.tueVet2 = tueVet2;
    }

    public String getTueVet3() {
        return tueVet3;
    }

    public void setTueVet3(String tueVet3) {
        this.tueVet3 = tueVet3;
    }

    public String getWedVet1() {
        return wedVet1;
    }

    public void setWedVet1(String wedVet1) {
        this.wedVet1 = wedVet1;
    }

    public String getWedVet2() {
        return wedVet2;
    }

    public void setWedVet2(String wedVet2) {
        this.wedVet2 = wedVet2;
    }

    public String getWedVet3() {
        return wedVet3;
    }

    public void setWedVet3(String wedVet3) {
        this.wedVet3 = wedVet3;
    }

    public String getThursVet1() {
        return thursVet1;
    }

    public void setThursVet1(String thursVet1) {
        this.thursVet1 = thursVet1;
    }

    public String getThursVet2() {
        return thursVet2;
    }

    public void setThursVet2(String thursVet2) {
        this.thursVet2 = thursVet2;
    }

    public String getThursVet3() {
        return thursVet3;
    }

    public void setThursVet3(String thursVet3) {
        this.thursVet3 = thursVet3;
    }

    public String getFriVet1() {
        return friVet1;
    }

    public void setFriVet1(String friVet1) {
        this.friVet1 = friVet1;
    }

    public String getFriVet2() {
        return friVet2;
    }

    public void setFriVet2(String friVet2) {
        this.friVet2 = friVet2;
    }

    public String getFriVet3() {
        return friVet3;
    }

    public void setFriVet3(String friVet3) {
        this.friVet3 = friVet3;
    }

    public String getSatVet1() {
        return satVet1;
    }

    public void setSatVet1(String satVet1) {
        this.satVet1 = satVet1;
    }

    public String getSatVet2() {
        return satVet2;
    }

    public void setSatVet2(String satVet2) {
        this.satVet2 = satVet2;
    }

    public String getSatVet3() {
        return satVet3;
    }

    public void setSatVet3(String satVet3) {
        this.satVet3 = satVet3;
    }

    public String getSunVet1() {
        return sunVet1;
    }

    public void setSunVet1(String sunVet1) {
        this.sunVet1 = sunVet1;
    }

    public String getSunVet2() {
        return sunVet2;
    }

    public void setSunVet2(String sunVet2) {
        this.sunVet2 = sunVet2;
    }

    public String getSunVet3() {
        return sunVet3;
    }

    public void setSunVet3(String sunVet3) {
        this.sunVet3 = sunVet3;
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
        if (!(object instanceof WorkingRotation)) {
            return false;
        }
        WorkingRotation other = (WorkingRotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.WorkingRotation[ id=" + id + " ]";
    }
    
}
