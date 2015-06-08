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
import uy.com.cinestar.domain.Movie;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class MoviePersistenceBean {

    @PersistenceContext
    EntityManager em;
    
     
    public boolean addMovie(Movie m){
        try{
            em.persist(m);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public List<Movie> getAllMovies(){
        try{
            Query query = em.createQuery("SELECT m from Movie as m");
            return query.getResultList();
        }catch(Exception e){
            return null;
        }
    }
    
}
