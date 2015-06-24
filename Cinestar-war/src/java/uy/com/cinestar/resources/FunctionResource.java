/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.sb.FunctionBean;
import uy.com.cinestar.sb.TicketBean;
import uy.com.cinestar.entities.Function;
import uy.com.cinestar.entities.Ticket;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;
import uy.com.cinestar.interceptors.SupervisorInterceptorBean;

@Path("Function")
public class FunctionResource {

    @Context
    private UriInfo context;

    @EJB
    private FunctionBean functionBean;

    @EJB
    private TicketBean ticketBean;

    @EJB
    private ExceptionResponseHelperBean exceptionHelper;

    public FunctionResource() {
    }

    @GET
    @Produces("application/json")
    public Response getFunctions() {
        try {
            List<Function> functions = functionBean.getFunctions();
            functions = this.ManitupateFunctionsData(functions);
            Gson responde = new GsonBuilder().create();
            return Response.accepted(responde.toJson(functions)).build();
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @GET
    @Path("Complex")
    @Produces("application/json")
    public Response getComplexFunctions(@QueryParam("complexId") Long complexId) {
        try {
            List<Function> functions = functionBean.getComplexFunctions(complexId);
            if (functions != null) {
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(functions)).build();
            } else {
                return Response.serverError().build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @GET
    @Path("Tickets")
    @Produces("application/json")
    public Response getFunctionTickets(@QueryParam("id") Long id) throws Exception {
        try {
            if (id == null) {
                throw new ParameterException("Debe enviar el id de la funcion en el primer parametro.", null);
            } else {
                List<Ticket> tickets = ticketBean.getFunctionTickets(id);
                tickets = this.ManipulateTicketsData(tickets);
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(tickets)).build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @GET
    @Path("Report")
    @Interceptors(SupervisorInterceptorBean.class)
    @Produces("application/json")
    public Response getFunctionFundraising(@QueryParam("token") UUID token, @QueryParam("id") Long id) throws Exception {
        try {
            if (id == null) {
                throw new ParameterException("Debe enviar el id de la funcion en el primer parametro.", null);
            } else {
                String resume = functionBean.getFunctionFundraising(id);
                return Response.accepted(resume).build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    private List<Function> ManitupateFunctionsData(List<Function> list) {
        Function fun;
        for (Function list1 : list) {
            fun = list1;
            fun.setTickets(null);
            fun.getRoom().setSeats(null);
        }
        return list;
    }

    private List<Ticket> ManipulateTicketsData(List<Ticket> list) {
        Ticket tick;
        for (Ticket list1 : list) {
            tick = list1;
            tick.setFunction(null);
            if (tick.getUser() != null) {
                tick.getUser().setPassword(null);
                tick.getUser().setType(null);
                tick.getUser().setTickets(null);
            }
        }
        return list;
    }
}
