/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.domain.User;


@Stateless
@LocalBean
public class UserPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
    
    public boolean addUser(User u){
        try{
            em.persist(u);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public List<User> getAllUsers(){
        Query query = em.createQuery("SELECT u from User as u");
        return query.getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
