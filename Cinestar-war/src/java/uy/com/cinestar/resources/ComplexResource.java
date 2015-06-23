/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.beans.ComplexBean;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;
import uy.com.cinestar.exceptions.ParameterException;


@Path("Complex")
public class ComplexResource {

    @Context
    private UriInfo context;

    @EJB
    private ComplexBean complexBean; 
    @EJB
    private ExceptionResponseHelperBean exceptionHelper;
    
    public ComplexResource() {
    }

    /**
     * Retrieves representation of an instance of uy.com.cinestar.resources.ComplexResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Path("Billboard")
    @Produces("application/json")
    public Response getBillboard(@QueryParam("id") Long id) {
        try{
            if (id==null)
                throw new ParameterException("Debe enviar el id del complejo en el request.",null);
            List<Movie> list = complexBean.getComplexBillboard(id);
            if (list!=null){
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(list)).build();
            }else{
                return Response.accepted("Ocurrio un error al acceder a la base de datos, "
                        + "puede que el id sea incorrecto.").build();
            }
        }catch (Exception ex){
            return exceptionHelper.exceptionResponse(ex);
        }
    }
    
    @PUT
    @Produces("application/json")
    public Response addMovieToBillboard(@QueryParam("complexId") Long complexId, @QueryParam("movieId") Long movieId) {
        try{
            if (complexId ==null || movieId==null){
                throw new ParameterException("Debe enviar el id del complejo y el id de la pelicula en el request.",null);
            }else{
                complexBean.addMovieToBillboard(complexId, movieId);
                return Response.accepted("Pelicula agregara satisfactoriamente a la cartelera.").build();
            }
        }catch (Exception ex){
            return exceptionHelper.exceptionResponse(ex);
        }
    }
    
    @PUT
    @Path("AddFunction")
    @Produces("application/json")
    public Response addFunction(@QueryParam("movieId") Long movieId, @QueryParam("complexRoomId") Long roomId,@QueryParam("startDate") Date startDate,@QueryParam("price") double price) {
        try{
            if (roomId ==null || movieId==null || startDate==null || price==0){
                throw new ParameterException("Debe enviar el id de la sala, el id de la pelicula, la fecha de inicio y el precio de la funcion en el request.",null);
            }else{
                complexBean.addFunction(roomId, movieId, startDate, price);
                return Response.accepted("Funcion creada y agregara satisfactoriamente.").build();
            }
        }catch (Exception ex){
            return exceptionHelper.exceptionResponse(ex);
        }
    }
    
}
