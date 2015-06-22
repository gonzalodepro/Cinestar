package uy.com.cinestar.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.persistence.MoviePersistenceBean;

@Stateless
@LocalBean
public class MovieBean {

    @EJB
    private MoviePersistenceBean persistence;
            
    public void addMovie (Movie m) throws CinestarException{
        persistence.addMovie(m);
    }
}
