/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.Room;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.persistence.RoomPersistenceBean;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class RoomBean {

    @EJB
    private RoomPersistenceBean persistence;
    
    public void addRoom(Room r)throws CinestarException{
        persistence.addRoom(r);
    }
    
}
