/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.*;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.generics.Enums;
import uy.com.cinestar.persistence.*;


@Singleton
@LocalBean
public class SistemBean { 
    
    @EJB
    private ComplexPersistenceBean complexPersistence;
    
    @EJB
    private RoomPersistenceBean roomPersistence;
    
    @EJB
    private MoviePersistenceBean moviePersistence;
    
    @EJB
    private FunctionPersistenceBean functionPersistence;
    
    @EJB
    private UserPersistenceBean userPersistence;
    
    private final Map<UUID, User> loggedUsers; 

    public SistemBean() 
    {
        this.loggedUsers = new HashMap<>();
    }

    public UUID UserLog(User u){
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
    
    public User IsCorrectToken(UUID token){
        if (this.loggedUsers.containsKey(token)){
            return loggedUsers.get(token);
        }else{
            return null;
        }
    }
    
    
    public void LoadDefaultValues() throws Exception{
        try{
            User u = new User();
            u.setNick("usu1");
            u.setPassword("pass1");
            u.setType(Enums.UserType.Client);
            
            User u2 = new User();
            u2.setNick("usu2");
            u2.setPassword("pass2");
            u2.setType(Enums.UserType.Administrator);
            
            userPersistence.addUser(u);
            userPersistence.addUser(u2);
            
            Movie m = new Movie();
            m.setTitle("Titanic");
            m.setDurationMin(120);
            m.setDescription("Pelicula apta para +18");
            moviePersistence.addMovie(m);
            
            Room r = new Room();
            r.setDescription("Sala teatro");
            r.setNumber(8);
            roomPersistence.addRoom(r);
            
            Room r2 = new Room();
            r2.setNumber(1);
            r2.setDescription("Di Caprio");
            
            Function f = new Function();
            f.setMovie(m);
            f.setRoom(r2);
            Date d= new Date();
            f.setStartDate(d);
            f.setPrice(149);
            functionPersistence.addFunction(f);
            
            Complex c = new Complex();
            c.setName("Movie montevideo Shopping");
            c.getRooms().add(r);
            c.getRooms().add(r2);
            c.addMovieToBillboard(m);
            c.getFunctions().add(f);
            complexPersistence.addComplex(c);
            
        }catch(Exception ex){
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos por defecto. Intente nuevamente", ex);
        }
    }
}
