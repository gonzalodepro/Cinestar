/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.entities.Complex;
import uy.com.cinestar.sb.ComplexBean;
import uy.com.cinestar.entities.Movie;
import uy.com.cinestar.entities.Room;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.interceptors.AdminInterceptorBean;

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
     * Retrieves representation of an instance of
     * uy.com.cinestar.resources.ComplexResource
     *
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Path("Billboard")
    @Produces("application/json")
    public Response getBillboard(@QueryParam("id") Long id) {
        try {
            if (id == null) {
                throw new ParameterException("Debe enviar el id del complejo en el request.", null);
            }
            List<Movie> list = complexBean.getComplexBillboard(id);
            if (list != null) {
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(list)).build();
            } else {
                return Response.accepted("Ocurrio un error al acceder a la base de datos, "
                        + "puede que el id sea incorrecto.").build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @GET
    @Produces("application/json")
    public Response getComplex() {
        try {
            List<Complex> list = complexBean.getAllComplex();
            if (list != null) {
                list = this.ManipulateComplexData(list);
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(list)).build();
            } else {
                return Response.accepted("Ocurrio un error al acceder a la base de datos, "
                        + "puede que el id sea incorrecto.").build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }
    
    @GET
    @Path("ComplexRooms")
    @Produces("application/json")
    public Response getComplex(@QueryParam("id") Long id) {
        try {
            if (id == null) {
                throw new ParameterException("Debe enviar el id del complejo en el request.", null);
            }
            List<Room> list = complexBean.getComplexRooms(id);
            if (list != null) {
                list = this.ManipulateRoomsData(list);
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(list)).build();
            } else {
                return Response.accepted("Ocurrio un error al acceder a la base de datos, "
                        + "puede que el id sea incorrecto.").build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @PUT
    @Interceptors(AdminInterceptorBean.class)
    @Produces("application/json")
    public Response addMovieToBillboard(@QueryParam("complexId") Long complexId, @QueryParam("movieId") Long movieId) {
        try {
            if (complexId == null || movieId == null) {
                throw new ParameterException("Debe enviar el id del complejo y el id de la pelicula en el "
                        + "request.", null);
            } else {
                complexBean.addMovieToBillboard(complexId, movieId);
                return Response.accepted("Pelicula agregara satisfactoriamente a la cartelera.").build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @POST
    @Path("NewFunction")
    @Interceptors(AdminInterceptorBean.class)
    public Response addFunction(@QueryParam("token") UUID token,
            @QueryParam("complexId") Long complexId,
            @QueryParam("roomId") Long roomId,
            @QueryParam("movieId") Long movieId,
            @QueryParam("price") double price,
            @QueryParam("day") int day,
            @QueryParam("month") int month,
            @QueryParam("year") int year,
            @QueryParam("hour") int hour,
            @QueryParam("min") int min) {
        try {
            if (complexId == null || roomId == null || movieId == null || price == 0
                    || year < 2015 || month < 1 || month > 12 || day < 1 || day > 31
                    || hour < 0 || hour > 24 || min < 0 || min > 59) {
                throw new ParameterException("Error! Compruebe que esta enviando todos los parametros "
                        + "y que son correctos (especialmente las fechas).", null);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = sdf.parse(day + "/" + month + "/" + year + " " + hour + ":" + min);

                complexBean.addFunction(complexId, roomId, movieId, date, price);
                return Response.accepted("Funcion creada y agregara satisfactoriamente.").build();
            }
        } catch (ParseException | CinestarException ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    private List<Complex> ManipulateComplexData(List<Complex> list) {
        for (Complex list1 : list) {
            list1.setFunctions(null);
            list1.setRooms(null);
            list1.setBillboard(null);
        }
        return list;
    }
    
    private List<Room> ManipulateRoomsData(List<Room> list) {
        for (Room room1 : list) {
            room1.setSeats(null);
        }
        return list;
    }

}   