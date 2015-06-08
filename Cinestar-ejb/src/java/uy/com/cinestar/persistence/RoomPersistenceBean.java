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
import uy.com.cinestar.domain.Room;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class RoomPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
     
    public boolean addComplex(Room r){
        try{
            em.persist(r);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
