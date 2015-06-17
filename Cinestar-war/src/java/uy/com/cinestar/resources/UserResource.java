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
import uy.com.cinestar.beans.SistemBean;
import uy.com.cinestar.beans.UserBean;
import uy.com.cinestar.domain.User;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.generics.Enums;
import uy.com.cinestar.generics.ExceptionHelperBean;

@Path("User")
public class UserResource {

    @Context
    private UriInfo context;
    
    
    @EJB
    private UserBean userBean;
    
    @EJB
    private ExceptionHelperBean exceptionHelper;
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
    @Interceptors(AdminInterceptorBean.class)
    public Response addAdmin(@QueryParam("token") UUID token,@QueryParam("nick") String nick,@QueryParam("password") String pass) {
        try{
            if (nick==null || pass==null){
                throw new ParameterException("Para agregar un Administrador al sistema debe enviar el nick y passowrd en el request.");
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
        }catch(Exception e){
            return exceptionHelper.exceptionResponse(e);
        }
        
    }
}
