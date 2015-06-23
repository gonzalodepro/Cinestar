
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

    public Response exceptionResponse(Exception e) {
//aca hacer switch entre mis excepciones.
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        //bad request , es , la persona le pifio al mandar los datos en el request x ej. 
        //el resto, problemas del servidor, es server error (500)
        // return Response.accepted(e.getMessage()).build();
    }
}
