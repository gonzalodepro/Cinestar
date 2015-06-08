/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.persistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.domain.Function;

@Stateless
@LocalBean
public class FunctionPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
    public List<Function> getAllFunctions(){
        try{
            Query query = em.createQuery("SELECT f from Function as f");
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
    
    public Function getFunction(long id){
        try{
            Query query = em.createQuery("SELECT f from Function as f where f.id=:id");
            query.setParameter("id", id);
            return (Function)query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    
    public boolean addFunction(Function f){
        try{
            em.persist(f);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean buySeatInFunction(Long id, int seatRow, int seatColumn){
        try{
            //ARMAR QUERY
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
