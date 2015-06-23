/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.jms.Connection;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.beans.FunctionBean;
import uy.com.cinestar.beans.TicketBean;
import uy.com.cinestar.domain.Function;
import uy.com.cinestar.domain.Ticket;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;
import uy.com.cinestar.exceptions.MdbException;

@Path("Function")
public class FunctionResource {

    @Context
    private UriInfo context;

    @Resource(mappedName = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/Topic")
    private Topic topic;

    @EJB
    private FunctionBean functionBean;

    @EJB
    private TicketBean ticketBean;

    @EJB
    private ExceptionResponseHelperBean exceptionHelper;

    public FunctionResource() {
    }

    @GET
    @Produces("application/json")
    public Response getFunctions() {
        try {
            List<Function> functions = functionBean.getFunctions();
            Gson responde = new GsonBuilder().create();
            this.notifSale("prueba de notificacion de email!!!!!!!!!");
            return Response.accepted(responde.toJson(functions)).build();
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @GET
    @Path("Complex")
    @Produces("application/json")
    public Response getComplexFunctions(@QueryParam("complexId") Long complexId) {
        try {
            List<Function> functions = functionBean.getComplexFunctions(complexId);
            if (functions != null) {
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(functions)).build();
            } else {
                return Response.serverError().build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @GET
    @Path("Tickets")
    @Produces("application/json")
    public Response getFunctionTickets(@QueryParam("id") Long id) throws Exception {
        try {
            if (id == null) {
                throw new ParameterException("Debe enviar el id de la funcion en el primer parametro.", null);
            } else {
                List<Ticket> tickets = ticketBean.getFunctionTickets(id);
                Gson responde = new GsonBuilder().create();
                return Response.accepted(responde.toJson(tickets)).build();
            }
        } catch (Exception ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    private void notifSale(String message) throws MdbException {
        try {
            Connection connection;
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(topic);
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setText(message);
            messageProducer.send(textMessage);
        } catch (JMSException ex) {
            throw new MdbException("Ocurrio un error al enviar el mensaje para el registro de la venta.", ex);
        }
    }
}
