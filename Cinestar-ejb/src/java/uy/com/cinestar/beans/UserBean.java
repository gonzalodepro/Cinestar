/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.User;
import uy.com.cinestar.persistence.UserPersistenceBean;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class UserBean {

    @EJB
    private UserPersistenceBean userPersistence;
    
    public boolean addUser(User u){
        return userPersistence.addUser(u);
    }
}
