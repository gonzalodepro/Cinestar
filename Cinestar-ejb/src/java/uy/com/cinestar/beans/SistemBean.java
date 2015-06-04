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
import uy.com.cinestar.domain.*;


@Singleton
@LocalBean
public class SistemBean {

    private final List<User> users;
    
    private final Map<UUID, User> loggedUsers; 

    public SistemBean() {
       
        this.users = new ArrayList<>();
        users.add(new Administrador("usu1","pass1"));
        users.add(new Administrador("usu2","pass2"));
        this.loggedUsers = new HashMap<>();
        
    }

    public List<User> getUsers() {
        return users;
    }
    
    public UUID UserLog(User u){
        UUID ret;
        if (users.contains(u)){
            ret = UUID.randomUUID();
            loggedUsers.put(ret, u);
        }else{
            ret = null;
        }
        return ret;
    }
    
    
    
}
