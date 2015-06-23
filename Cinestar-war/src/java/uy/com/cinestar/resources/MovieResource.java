/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.beans.MovieBean;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;

/**
 * REST Web Service
 *
 * @author Gonza
 */
@Path("Movie")
public class MovieResource {

    @Context
    private UriInfo context;

    @EJB
    private ExceptionResponseHelperBean exceptionHelper;
    @EJB

    private MovieBean movieBean;

    public MovieResource() {
    }

    @GET
    @Produces("application/json")
    public Response getMovie(@QueryParam("id") Long id) {
        try {
            if (id == null) {
                List<Movie> movies = movieBean.getAllMovies();
                if (movies != null) {
                    if (movies.isEmpty()) {
                        return Response.accepted("No hay peliculas ingresadas en el sistema.").build();
                    } else {
                        Gson responde = new GsonBuilder().create();
                        return Response.accepted(responde.toJson(movies)).build();
                    }
                } else {
                    return Response.accepted("No hay peliculas ingresadas en el sistema.").build();
                }
            } else {
                Movie m = movieBean.getMovie(id);
                if (m == null) {
                    return Response.accepted("La pelicula solicitada no existe.").build();
                } else {
                    Gson responde = new GsonBuilder().create();
                    return Response.accepted(responde.toJson(m)).build();
                }
            }
        } catch (Exception e) {
            return exceptionHelper.exceptionResponse(e);
        }
    }

    @PUT
    public Response addMovie(@QueryParam("title") String title, @QueryParam("description") String desc, @QueryParam("duration") int dur) {
        try {
            if (title == null || desc == null || dur == 0) {
                throw new ParameterException("Para agregar una pelicula debe enviar su titulo, descripcion y duracion (mayor a cero) en el request.", null);
            }
            Movie m = new Movie();
            m.setTitle(title);
            m.setDescription(desc);
            m.setDurationMin(dur);
            movieBean.addMovie(m);
            return Response.accepted("Pelicula agregara satistactoriamente.").build();
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @POST
    @Path("Update")
    public Response updateMovie(@QueryParam("id") Long id, @QueryParam("title") String title, @QueryParam("description") String desc, @QueryParam("duration") int dur) {
        try {
            if (id == null) {
                throw new ParameterException("Para modificar una pelicula debe enviar el id en el primer parametro.", null);
            }
            movieBean.updateMovie(id, title, desc, dur);
            return Response.accepted("Pelicula modificada satisfactoriamente.").build();
        } catch (Exception e) {
            return exceptionHelper.exceptionResponse(e);
        }
    }

    @DELETE
    public Response deleteMovie(@QueryParam("id") Long id) {
        try {
            if (id == null) {
                throw new ParameterException("Para eliminar una pelicula debe enviar el id en el primer parametro.", null);
            }
            movieBean.deleteMovie(id);
            return Response.accepted("Pelicula eliminada satisfactoriamente.").build();
        } catch (Exception e) {
            return exceptionHelper.exceptionResponse(e);
        }
    }
}
