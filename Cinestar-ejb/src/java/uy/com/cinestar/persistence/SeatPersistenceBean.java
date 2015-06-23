/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.persistence;

import com.sun.xml.ws.rx.rm.runtime.sequence.persistent.PersistenceException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.domain.Seat;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class SeatPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
     
    public void addSeat(Seat s) throws CinestarException{
        try{
            em.persist(s);
        }catch(javax.persistence.EntityExistsException ex){
            throw new EntityExistsException("Error! Ya existe un asiento con ese id en la base de datos.",ex);
        }catch (PersistenceException ex){
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos del asiento. Intente nuevamente",ex);
        }catch (Exception ex){
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al intentar persistir el asiento. Intente nuevamente. Si el error persiste contactese con soporte.",ex);
        }
    }
    
    public List<Seat> getRoomSeats(Long roomId){
        try{
            Query query = em.createQuery("ARMAR QUEY");
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
