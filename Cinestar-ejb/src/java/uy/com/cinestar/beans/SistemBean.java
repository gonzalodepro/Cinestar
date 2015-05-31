/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.*;


@Singleton
@LocalBean
public class SistemBean {

    private final List<User> users;
    
    

    public SistemBean() {
        this.users = new ArrayList<>();
        users.add(new Administrador("test","pass"));
        users.add(new Administrador("test2","pass2"));
        
    }

    public List<User> getUsers() {
        return users;
    }
    
    public boolean UserExist(User u){
        return users.contains(u);
    }
    
    
    
}
