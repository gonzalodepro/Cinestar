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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.beans.PruebaBean;
import uy.com.cinestar.beans.SistemBean;
import uy.com.cinestar.domain.Administrador;
import uy.com.cinestar.domain.User;


@Path("Log")
public class LogResource {

    @Context
    private UriInfo context;
    
    @EJB
    private SistemBean sistem;

    @EJB
    private PruebaBean pruebaBean;
    /**
     * Creates a new instance of LogResource
     */
    public LogResource() {
    }

    /**
     * Retrieves representation of an instance of uy.com.cinestar.resources.LogResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @POST
    @Produces("application/json")
    @Path("loggin")
    public Response logg(@QueryParam("nick") String nick, @QueryParam("password") String password) {
        UUID uuidToken= sistem.UserLog(new Administrador(nick, password));
        String logResult;
        if (uuidToken==null){
            logResult = "Usuario/contraseña incorrectos.";
        }else{
            logResult="Usuario loggeado correctamente. Token: " + uuidToken.toString();
        }
        
        Gson responde = new GsonBuilder().create();
        return Response.accepted(responde.toJson(logResult)).build();
    }

    @POST
    @Produces("application/json")
    @Path("auto")
    public Response auto(@QueryParam("matricula") String matricula, @QueryParam("año") Integer año) throws Exception {
        
        pruebaBean.crearAuto(matricula, año);
        Gson responde = new GsonBuilder().create();
        return Response.accepted(responde.toJson("ok")).build();
    }
    
    /**
     * PUT method for updating or creating an instance of LogResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
