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
public class managingStaffFacade extends AbstractFacade<managingStaff> {

    @PersistenceContext(unitName = "AVCS-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public managingStaffFacade() {
        super(managingStaff.class);
    }
    public void updateById(long id, int password, String email, String gender){
        Query q = em.createNamedQuery("managingStaff.updateById");
        q.setParameter("id", id);
        q.setParameter("intpassword", password);
        q.setParameter("email", email);
        q.setParameter("gender", gender);
        q.executeUpdate();
    }
    
    public void deleteById(long id){
        Query q = em.createNamedQuery("managingStaff.deleteById");
        q.setParameter("id", id);
        q.executeUpdate();
    }
}
