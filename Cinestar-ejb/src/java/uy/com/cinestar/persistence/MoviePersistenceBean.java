/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.exceptions.DataAccesException;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class MoviePersistenceBean {

    @PersistenceContext
    EntityManager em;
    
     
    public boolean addMovie(Movie m) throws DataAccesException{
        try{
            em.persist(m);
            return true;
        }catch(Exception e){
            throw new DataAccesException("Disculpe! Ocurrio un error al intentar persistir la pelicula " + m.getTitle()+". Intente nuevamente");
        }
    }
    
    public List<Movie> getAllMovies() throws DataAccesException{
        try{
            Query query = em.createQuery("SELECT m from Movie as m");
            return query.getResultList();
        }catch(Exception e){
            throw new DataAccesException("Disculpe! Ocurrio un error al consultar las peliculas. Intente nuevamente");
        }
    }
    
    public Movie getMovie(Long id) throws DataAccesException{
        try{
            Movie m = em.find(Movie.class, id);
            return m;    
        }catch(Exception e){
            throw new DataAccesException("Disculpe! Ocurrio un error al consultar los datos de la pelicula. Intente nuevamente");
        }
    }
    public void updateMovie(Long id, String title, String description, int duration) throws DataAccesException{
        try{
            Movie m = em.find(Movie.class, id);
            if (title !=null)
                m.setTitle(title);
            if (description !=null)
                m.setDescription(description);
            if (duration!=0)
                m.setDurationMin(duration);
            em.refresh(m);
        }catch(Exception e){
            throw new DataAccesException("Disculpe! Ocurrio un error al modificar los datos de la pelicula. Puede que el id sea incorrecto. Intente nuevamente");
        }
    }
}
