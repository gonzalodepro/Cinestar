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
import uy.com.cinestar.domain.Complex;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.NoDataException;
import uy.com.cinestar.persistence.ComplexPersistenceBean;
import uy.com.cinestar.persistence.MoviePersistenceBean;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class ComplexBean {

    @EJB
    private ComplexPersistenceBean persistence;
    
    @EJB
    private MoviePersistenceBean moviePersistence;
    
    public List<Complex> getAllComplex() throws CinestarException{
        return persistence.getAllComplex();
    }
    
    public Complex getComplex(Long id) throws NoDataException, CinestarException{
        return persistence.getComplex(id);
    }
    
    public List<Movie> getComplexBillboard(Long id) throws NoDataException, CinestarException{
        return persistence.getComplexBillboard(id);
    }
    public void addComplex(Complex comp) throws CinestarException{
        persistence.addComplex(comp);
    }
    public void addMovieToBillboard(Long complexId, Long movieId) throws CinestarException{
        Movie movie = moviePersistence.getMovie(movieId);
        Complex complex = persistence.getComplex(complexId);
        complex.addMovieToBillboard(movie);
        persistence.updateComplex(complex);
    }
    
}
