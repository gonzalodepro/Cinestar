package uy.com.cinestar.beans;

import java.util.List;
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
    public Movie getMovie(Long id) throws CinestarException{
        return persistence.getMovie(id);
    }
    
    public void updateMovie(Long id, String title, String desc, int dur) throws CinestarException{
        persistence.updateMovie(id, title, desc, dur);
    }
    
    public void deleteMovie(Long id) throws CinestarException{
        persistence.deleteMovie(id);
    }
    public List<Movie> getAllMovies() throws CinestarException{
        return persistence.getAllMovies();
    }
}
