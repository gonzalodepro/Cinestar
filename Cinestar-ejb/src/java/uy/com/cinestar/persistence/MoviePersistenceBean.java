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
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.entities.Movie;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.exceptions.NoDataException;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class MoviePersistenceBean {

    @PersistenceContext
    EntityManager em;

    public void addMovie(Movie movie) throws CinestarException {
        try {
            em.persist(movie);
        } catch (javax.persistence.EntityExistsException ex) {
            throw new EntityExistsException("Error! Ya existe una pelicula con ese id en la base de datos.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos de la "
                    + "pelicula. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al intentar persistir la "
                    + "pelicula. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public List<Movie> getAllMovies() throws CinestarException {
        try {
            Query query = em.createQuery("SELECT m from Movie as m");
            return query.getResultList();
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al consultar los datos de las pelicula. "
                    + "Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al consultar las peliculas. "
                    + "Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public Movie getMovie(Long id) throws CinestarException {
        try {
            Query query = em.createQuery("SELECT m from Movie as m WHERE m.id=:id");
            query.setParameter("id", id);
            return (Movie) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new NoDataException("Error! No existe una pelicula con el id: " + id + " en el sistema.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener los datos de la pelicula. "
                    + "Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener los datos de la "
                    + "pelicula. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public void updateMovie(Long id, String title, String description, int duration) throws CinestarException {
        try {
            Movie m = em.find(Movie.class, id);
            if (!title.equals("")) {
                m.setTitle(title);
            }
            if (!description.equals("")) {
                m.setDescription(description);
            }
            if (duration != 0) {
                m.setDurationMin(duration);
            }

            em.merge(m);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al modificar los datos de la "
                    + "pelicula. Puede que el id sea incorrecto. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al modificar los datos de "
                    + "la pelicula. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public void deleteMovie(Long id) throws CinestarException {
        try {
            Movie movie = em.find(Movie.class, id);
            em.remove(movie);
        } catch (PersistenceException ex) {
            throw new PersistenceException("Disculpe! Ocurrio un error al eliminar la pelicula. Puede que "
                    + "el id sea incorrecto. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al eliminar la pelicula. "
                    + "Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }

    }
}
