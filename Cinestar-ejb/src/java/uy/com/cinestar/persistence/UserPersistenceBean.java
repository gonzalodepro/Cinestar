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
import uy.com.cinestar.domain.User;
import uy.com.cinestar.generics.Enums;

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
        try{
            Query query = em.createQuery("SELECT u from User as u");
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
    
    public User getUser(Long id){
        try{
            Query query = em.createQuery("SELECT u from User as u WHERE u.id=:id");
            query.setParameter("id", id);
            User u = (User)query.getSingleResult();
            return u;
        }catch(Exception e){
            return null;
        }
    }
    
}
