package uy.com.cinestar.resources;

import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.ExceptionResponseHelperBean;
import uy.com.cinestar.exceptions.MdbException;
import uy.com.cinestar.exceptions.ParameterException;
import uy.com.cinestar.interceptors.ClientInterceptorBean;
import uy.com.cinestar.sb.TicketBean;

/**
 * REST Web Service
 *
 * @author Gonza
 */
@Path("Ticket")
public class TicketResource {

    @Context
    private UriInfo context;

    @Resource(mappedName = "jms/connectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/Topic")
    private Topic topic;
    
    @EJB
    private TicketBean ticketBean;

    @EJB
    private ExceptionResponseHelperBean exceptionHelper;

    /**
     * Creates a new instance of TicketResource
     */
    public TicketResource() {
    }

    /**
     * Retrieves representation of an instance of
     * uy.com.cinestar.resources.TicketResource
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Interceptors(ClientInterceptorBean.class)
    @Path("Buy")
    public Response buyTickets(@QueryParam("token") UUID token, @QueryParam("ticketId") List<Long> ticketsId) throws CinestarException {
        try {
            if (ticketsId == null || ticketsId.isEmpty()) {
                throw new ParameterException("Debe enviar la lista de tickets a comprar en el request.", null);
            } else {
                String emailText = ticketBean.buyTickets(token, ticketsId);
                
                notifPurchase(emailText);
                return Response.accepted("Tickets comprados con exito.").build();

            }
        } catch (CinestarException ex) {
            return exceptionHelper.exceptionResponse(ex);
        }
    }

    
    
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    private void notifPurchase(String message) throws MdbException {
        try {
            Connection connection;
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(topic);
            TextMessage textMessage = session.createTextMessage(message);
            textMessage.setText(message);
            messageProducer.send(textMessage);
        } catch (JMSException ex) {
            throw new MdbException("Ocurrio un error al enviar el mensaje para la notificacion de la venta."
                    + " Contenido del mensaje a enviar: \n" + message, ex);
        }
    }
}
