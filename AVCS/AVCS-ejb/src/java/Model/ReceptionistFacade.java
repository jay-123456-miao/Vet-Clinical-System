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
public class ReceptionistFacade extends AbstractFacade<Receptionist> {

    @PersistenceContext(unitName = "AVCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReceptionistFacade() {
        super(Receptionist.class);
    }
    public Receptionist searchByUsername(String username){
        Query q = em.createNamedQuery("Receptionist.searchByUsername");
        q.setParameter("username", username);
        List<Receptionist> result = q.getResultList();
        if(result.size()>0){
            return result.get(0);
        }
        return null;
        }
    
    public List<Receptionist> searchNotApproved(){
        Query q = em.createNamedQuery("Receptionist.searchNotApproved");
        List<Receptionist> result = q.getResultList();
        if(result.size()>0){
            return result;
        }
        return null;
    }
    
    
    public void approveRegistration(long id, String status){
        Query q = em.createNamedQuery("Receptionist.approveRegistration");
        q.setParameter("id", id);
        q.setParameter("status", status);
        q.executeUpdate();
    }
}
