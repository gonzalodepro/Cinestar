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
import uy.com.cinestar.generics.Enums;


@Stateless
@LocalBean
public class UserPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
    public void LoadDefaultUsers(){
        User u1 = new User();
        User u2 = new User();
        
        u1.setNick("usu1");
        u1.setPassword("pass1");
        u1.setType(Enums.UserType.Client);
        u2.setNick("usu2");
        u2.setPassword("pass2");
        u2.setType(Enums.UserType.Client);
        em.getTransaction().begin();
        em.persist(u1);
        em.persist(u2);
        em.getTransaction().commit();
        
    }
    
    public boolean addUser(User u){
        try{
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            em.getTransaction().rollback();
            return false;
        }
    }
    
    public List<User> getAllUsers(){
        Query query = em.createQuery("SELECT u from User as u");
        return query.getResultList();
        
    }
    
}
