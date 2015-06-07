/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.persistence;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class SeatPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
    public boolean buySeat(long id){
        
        try{
            em.getTransaction().commit();
            Query query = em.createQuery("UPDATE Seat SET available=false WHERE id=:id");
            query.setParameter("id", id);
            int updatedRows = query.executeUpdate();
            em.getTransaction().commit();
            if (updatedRows==0)
                return false;
            else
                return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            return false;
        }
        
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
