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
public class VetFacade extends AbstractFacade<Vet> {

    @PersistenceContext(unitName = "AVCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VetFacade() {
        super(Vet.class);
    }
    
    public Vet searchByUsername(String username){
        Query q = em.createNamedQuery("Vet.searchByUsername");
        q.setParameter("username", username);
        List<Vet> result = q.getResultList();
        if(result.size()>0){
            return result.get(0);
        }
        return null;
        }
    public List<Vet> searchNotApproved(){
        Query q = em.createNamedQuery("Vet.searchNotApproved");
        List<Vet> result = q.getResultList();
        if(result.size()>0){
            return result;
        }
        return null;
    }
    
    
    public void approveRegistration(long id, String status){
        Query q = em.createNamedQuery("Vet.approveRegistration");
        q.setParameter("id", id);
        q.setParameter("status", status);
        q.executeUpdate();
    }
}
