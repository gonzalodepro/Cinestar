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
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.beans.ComplexBean;
import uy.com.cinestar.domain.Movie;


@Path("Complex")
public class ComplexResource {

    @Context
    private UriInfo context;

    @EJB
    private ComplexBean complexBean; 
    
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
            List<Movie> list = complexBean.getComplexBillboard(id);
            if (list!=null){
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(list)).build();
            }else{
                return Response.accepted("Ocurrio un error al acceder a la base de datos, puede que el id sea incorrecto.").build();
            }
        }catch (Exception e){
            return Response.serverError().build();
        }
    }
    
}
