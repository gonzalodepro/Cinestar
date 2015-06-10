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
import uy.com.cinestar.domain.Complex;
import uy.com.cinestar.domain.Complex;
import uy.com.cinestar.domain.Movie;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class ComplexPersistenceBean {
    
    @PersistenceContext
    EntityManager em;
    
    public boolean addComplex(Complex u){
        try{
            em.persist(u);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public List<Complex> getAllComplex(){
        try{
            Query query = em.createQuery("SELECT c from Complex as c");
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
    public Complex getComplex (Long id){
        try{
            Query query = em.createQuery("SELECT c from Complex as c WHERE c.id=:id");
            query.setParameter("id", id);
            return (Complex)query.getSingleResult();
            
        }catch (Exception e){
            return null;
        }
    }
    
    public List<Movie> getComplexBillboard(Long id){
        try{
            Complex theComplex =em.find(Complex.class, id);
            return theComplex.getBillboard();
        }catch(Exception e){
            return null;
        }
    }
    
}
