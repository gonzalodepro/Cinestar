package uy.com.cinestar.sb;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

import uy.com.cinestar.entities.Complex;
import uy.com.cinestar.entities.Function;
import uy.com.cinestar.entities.Movie;
import uy.com.cinestar.entities.Room;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.NoDataException;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.persistence.ComplexPersistenceBean;
import uy.com.cinestar.persistence.FunctionPersistenceBean;
import uy.com.cinestar.persistence.MoviePersistenceBean;
import uy.com.cinestar.persistence.RoomPersistenceBean;

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

    @EJB
    private RoomPersistenceBean roomPersistence;

    @EJB
    private FunctionPersistenceBean functionPersistence;

    public List<Complex> getAllComplex() throws CinestarException {
        return persistence.getAllComplex();
    }

    public Complex getComplex(Long id) throws NoDataException, CinestarException {
        return persistence.getComplex(id);
    }

    public List<Movie> getComplexBillboard(Long id) throws NoDataException, CinestarException {
        return persistence.getComplexBillboard(id);
    }

    public void addComplex(Complex comp) throws CinestarException {
        persistence.addComplex(comp);
    }

    public void addMovieToBillboard(Long complexId, Long movieId) throws CinestarException {
        Movie movie = moviePersistence.getMovie(movieId);
        Complex complex = persistence.getComplex(complexId);
        if (!complex.getBillboard().contains(movie)) {
            complex.addMovieToBillboard(movie);
            persistence.updateComplex(complex);
        }
    }
    
    public List<Room> getComplexRooms(Long id) throws CinestarException{
        return persistence.getComplex(id).getRooms();
    }

    public void addFunction(Long complexId, Long roomId, Long movieId, Date startDate, double price) throws CinestarException {

        Complex complex = persistence.getComplexFromRoom(roomId);
        if (!Objects.equals(complex.getId(), complexId)) {
            throw new ParameterException("Error! La sala no pertenece al complejo que quiere agregar la funcion.", null);
        } else {
            Movie movie = moviePersistence.getMovie(movieId);
            if (!complex.getBillboard().contains(movie)) {
                throw new ParameterException("Error! La pelicula no pertenece a la cartelera "
                        + "del complejo al cual quiere agregar la funcion.", null);
            }
            Function theFunction = new Function();
            Room room = roomPersistence.getRoom(roomId);
            theFunction.setMovie(movie);
            theFunction.setPrice(price);
            theFunction.setStartDate(startDate);
            theFunction.setRoom(room);
            functionPersistence.addFunction(theFunction);
        }
    }

}
