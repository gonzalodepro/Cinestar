package uy.com.cinestar.exceptions;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ws.rs.core.Response;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class ExceptionResponseHelperBean {

    public Response exceptionResponse(Exception ex) {

        if (ex.getClass().equals(CinestarException.class)) {

            if (ex.getClass().equals(ParameterException.class)) {
                return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
            } else if (ex.getClass().equals(LoginException.class)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity(ex.getMessage()).build();
            } else {
                return Response.serverError().entity(ex.getMessage()).build();
            }

        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
}
