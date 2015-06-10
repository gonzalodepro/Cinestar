/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.beans.SistemBean;
import uy.com.cinestar.beans.UserBean;
import uy.com.cinestar.domain.User;
import uy.com.cinestar.generics.Enums;

@Path("User")
public class UserResource {

    @Context
    private UriInfo context;
    
    @EJB
    private SistemBean sistem;
    
    @EJB
    private UserBean userBean;
    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of uy.com.cinestar.resources.UserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    
    @PUT
    @Path("Client")
    public Response addClient(@QueryParam("nick") String nick,@QueryParam("password") String pass) {
        if (nick==null || pass==null){
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }else{
            User u = new User();
            u.setType(Enums.UserType.Client);
            u.setNick(nick);
            u.setPassword(pass);
            boolean result = userBean.addUser(u);
            if (result)
                return Response.accepted("Usuario ingresado correctamente.").build();
            else
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PUT
    @Path("Admin")
    public Response addAdmin(@QueryParam("nick") String nick,@QueryParam("password") String pass) {
        if (nick==null || pass==null){
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }else{
            User u = new User();
            u.setType(Enums.UserType.Administrator);
            u.setNick(nick);
            u.setPassword(pass);
            boolean result = userBean.addUser(u);
            if (result)
                return Response.accepted("Usuario ingresado correctamente.").build();
            else
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        
    }
}
