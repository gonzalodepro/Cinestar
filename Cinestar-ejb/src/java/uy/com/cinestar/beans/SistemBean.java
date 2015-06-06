/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.*;
import uy.com.cinestar.generics.Enums;
import uy.com.cinestar.persistence.*;


@Singleton
@LocalBean
public class SistemBean { 
    
    @EJB
    private UserPersistenceBean userPersistence;
    
    private final Map<UUID, User> loggedUsers; 
    

    public SistemBean() 
   {
        this.loggedUsers = new HashMap<>();
        LoadUsers();
    }

    private void LoadUsers(){
        User u1 = new User();
        u1.setNick("usu1");
        u1.setPassword("pass1");
        u1.setType(Enums.UserType.Client);
        User u2 = new User();
        u2.setNick("usu2");
        u2.setPassword("pass2");
        u2.setType(Enums.UserType.Client);
        userPersistence.addUser(u1);
        userPersistence.addUser(u2);
        
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
