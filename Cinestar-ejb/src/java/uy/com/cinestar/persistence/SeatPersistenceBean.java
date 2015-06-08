/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.persistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.domain.Seat;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class SeatPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
     
    public boolean addSeat(Seat s){
        try{
            em.persist(s);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public boolean buySeat(long id){
        
        try{
            Query query = em.createQuery("UPDATE Seat SET available=false WHERE id=:id");
            query.setParameter("id", id);
            int updatedRows = query.executeUpdate();
            return updatedRows != 0;
        }catch (Exception e){
            return false;
        }
        
    }
    
    public List<Seat> getRoomSeats(Long roomId){
        try{
            Query query = em.createQuery("ARMAR QUEY");
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
