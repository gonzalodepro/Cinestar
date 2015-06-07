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
    }

    public UUID UserLog(User u){
        userPersistence.LoadDefaultUsers();
        UUID ret;
        List<User> list = userPersistence.getAllUsers();
        if (list.contains(u)){
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
    
    public boolean addUser(User u){
        return userPersistence.addUser(u);
    }
}
