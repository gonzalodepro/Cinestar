/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;
import uy.com.cinestar.sb.SystemBean;

/**
 * REST Web Service
 *
 * @author Gonza
 */
@Path("Generic")
public class GenericResource {

    @Context
    private UriInfo context;

    @EJB
    private SystemBean system;

    @PUT
    public Response loadDefaultValues(String content) {
        try {
            system.LoadDefaultValues();
            return Response.accepted("Datos por defecto cargados correctamente.").build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
    }
}
