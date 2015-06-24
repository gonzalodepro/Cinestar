/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.sb.SystemBean;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;

@Path("Log")
public class LogResource {

    @Context
    private UriInfo context;

    @EJB
    private ExceptionResponseHelperBean exceptionHelper;

    @EJB
    private SystemBean system;

    /**
     * Creates a new instance of LogResource
     */
    public LogResource() {
    }

    /**
     * Retrieves representation of an instance of
     * uy.com.cinestar.resources.LogResource
     *
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
    @Path("login")
    public Response log(@QueryParam("nick") String nick, @QueryParam("password") String password) {
        try {
            User user = new User();
            user.setNick(nick);
            user.setPassword(password);
            UUID uuidToken = system.UserLog(user);
            String logResult;
            if (uuidToken == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Usuario/contraseña incorrectos.").build();
            } else {
                return Response.accepted("Usuario loggeado correctamente. Token: " + uuidToken.toString()).build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
