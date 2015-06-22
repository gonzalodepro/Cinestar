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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.beans.FunctionBean;
import uy.com.cinestar.domain.Function;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;

@Path("Function")
public class FunctionResource {

    @Context
    private UriInfo context;
    
    @EJB
    private FunctionBean functionBean;
    
    @EJB
    private ExceptionResponseHelperBean exceptionHelper;
    
    public FunctionResource() {
    }
    
    @GET
    @Produces("application/json")
    public Response getFunctions() {
        try{
            List<Function> functions = functionBean.getFunctions();
            if (functions!=null) {
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(functions)).build();
            } else
                return Response.serverError().build();
        }catch(Exception ex){
            return exceptionHelper.exceptionResponse(ex);
        }
    }
    
    @GET
    @Path("Complex")
    @Produces("application/json")
    public Response getComplexFunctions(@QueryParam("complexId") Long complexId) {
        try{
            List<Function> functions = functionBean.getComplexFunctions(complexId);
            if (functions!=null) {
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(functions)).build();
            } else
                return Response.serverError().build();
        }catch(Exception ex){
            return exceptionHelper.exceptionResponse(ex);
        }
    }
    
    
    
    
    @GET
    @Path("h")
    @Produces("application/json")
    public Response getFunctionSeats(@QueryParam("id") Long id) throws Exception {
        if (id == null){
            throw new ParameterException("Debe enviar el id de la funcion en el primer parametro.",null);
        }else{
            try{
                Function function = functionBean.getFunction(id);
                if (function==null)
                    return Response.serverError().build();
                else{
                    Gson responde = new GsonBuilder().create();
                    return Response.accepted(responde.toJson(function.getRoom().getSeats())).build();
                }
            }catch(Exception e){
                return exceptionHelper.exceptionResponse(e);
            }
        }
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
