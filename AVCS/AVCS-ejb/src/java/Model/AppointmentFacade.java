/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "AVCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }
    
    public List searchByStatus(String username)
    {
        Query q = em.createNamedQuery("Appointment.searchUnfinishedAppointments");
        q.setParameter("vet_username", username);
        List<Receptionist> result = q.getResultList();
        if(result.size()>0){
            return result;
        }
        return null;
    }
        
    public List searchFinishedAppointments(String username)
    {
        Query q = em.createNamedQuery("Appointment.searchFinishedAppointments");
        q.setParameter("vet_username", username);
        List<Receptionist> result = q.getResultList();
        if(result.size()>0){
            return result;
        }
        return null;
    }
    
}
