/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.User;
import uy.com.cinestar.exceptions.CinestarException;
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
    
    public void addUser(User u) throws CinestarException{
        userPersistence.addUser(u);
    }
    public List<User> getAllUsers() throws CinestarException{
        return userPersistence.getAllUsers();
    }
}
