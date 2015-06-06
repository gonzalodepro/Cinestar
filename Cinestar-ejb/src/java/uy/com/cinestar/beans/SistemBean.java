/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.cinestar.domain.*;


@Singleton
@LocalBean
public class SistemBean {

    
    @PersistenceContext
    EntityManager em;
    private final Map<UUID, User> loggedUsers; 
    

    public SistemBean() throws Exception {
       
        this.loggedUsers = new HashMap<>();
        LoadUsers();
    }

    private void LoadUsers() throws Exception{
//        User u1 = new User("usu1","pass1");
//        User u2 = new User("usu2","pass2");
        User u1 = new User();
        u1.setNick("usu1");
        u1.setPassword("pass1");
        User u2 = new User();
        u2.setNick("usu2");
        u2.setPassword("pass2");
        try{
            em.persist(u1);
            em.persist(u2);
            
        }catch(Exception e){
            throw new Exception("Error al cargar los usuarios del sistema.");
        }
        
        
    }
    public List<User> getUsers() {
        return null;
    }
    
    public UUID UserLog(User u){
        UUID ret;
        if (getUsers().contains(u)){
            ret = UUID.randomUUID();    
            loggedUsers.put(ret, u);
        }else{
            ret = null;
        }
        return ret;
    }
    
    public boolean IsCorrectToken(UUID token){
        
        return (this.loggedUsers.containsKey(token));
        
    }
    
}
