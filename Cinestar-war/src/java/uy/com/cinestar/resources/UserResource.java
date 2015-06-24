/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import java.util.UUID;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.interceptors.AdminInterceptorBean;
import uy.com.cinestar.sb.UserBean;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.common.Enums;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;
import uy.com.cinestar.interceptors.SupervisorInterceptorBean;

@Path("User")
public class UserResource {

    @Context
    private UriInfo context;

    @EJB
    private UserBean userBean;

    @EJB
    private ExceptionResponseHelperBean exceptionHelper;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of
     * uy.com.cinestar.resources.UserResource
     *
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
    public Response addClient(@QueryParam("nick") String nick, @QueryParam("password") String pass) {
        try {
            if (nick == null || pass == null) {
                throw new ParameterException("Para agregar un Cliente al sistema debe enviar el nick y el password en el request.", null);
            } else {
                User u = new User();
                u.setType(Enums.UserType.Client);
                u.setNick(nick);
                u.setPassword(pass);
                userBean.addUser(u);
                return Response.accepted("Usuario ingresado correctamente.").build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @PUT
    @Path("Admin")
    @Interceptors(AdminInterceptorBean.class)
    public Response addAdmin(@QueryParam("token") UUID token, @QueryParam("nick") String nick, @QueryParam("password") String pass) {
        try {
            if (nick == null || pass == null) {
                throw new ParameterException("Para agregar un Administrador al sistema debe enviar el nick y passowrd en el request.", null);
            } else {
                User u = new User();
                u.setType(Enums.UserType.Administrator);
                u.setNick(nick);
                u.setPassword(pass);
                userBean.addUser(u);
                return Response.accepted("Usuario ingresado correctamente.").build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }
    
    @PUT
    @Path("Supervisor")
    @Interceptors(SupervisorInterceptorBean.class)
    public Response addSupervisor(@QueryParam("token") UUID token, @QueryParam("nick") String nick, @QueryParam("password") String pass) {
        try {
            if (nick == null || pass == null) {
                throw new ParameterException("Para agregar un Administrador al sistema debe enviar el nick y passowrd en el request.", null);
            } else {
                User u = new User();
                u.setType(Enums.UserType.Supervisor);
                u.setNick(nick);
                u.setPassword(pass);
                userBean.addUser(u);
                return Response.accepted("Usuario ingresado correctamente.").build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }
}
