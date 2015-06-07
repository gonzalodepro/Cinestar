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

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class FunctionPersistenceBean {

    @PersistenceContext
    EntityManager em;
    
    public List<Function> getAllFunctions(){
        Query query = em.createQuery("SELECT f from Function as f");
        return query.getResultList();
    }
    
    public boolean addFunction(Function f){
        try{
            em.persist(f);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
