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
import uy.com.cinestar.beans.SistemBean;
import uy.com.cinestar.domain.Function;

@Path("Function")
public class FunctionResource {

    @Context
    private UriInfo context;

    @EJB
    private SistemBean sistem;
    
    public FunctionResource() {
    }
    
    @GET
    @Produces("application/json")
    public Response getFunctions() {
        try{
            List<Function> functions = sistem.getFunctions();
            if (functions==null)
                return Response.serverError().build();
            else{
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(functions)).build();
            }
        }catch(Exception e){
            return Response.serverError().build();
        }
    }
    
    @GET
    @Path("h")
    @Produces("application/json")
    public Response getFunctionSeats(@QueryParam("id") Long id) {
        if (id == null){
            return Response.accepted("Debe enviar el id de la funcion en el primer parametro.").build();
        }else{
            try{
                Function function = sistem.getFunction(id);
                if (function==null)
                    return Response.serverError().build();
                else{
                    Gson responde = new GsonBuilder().create();
                    return Response.accepted(responde.toJson(function.getRoom().getSeats())).build();
                }
            }catch(Exception e){
                return Response.serverError().build();
            }
        }
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
