package uy.com.cinestar.persistence;

import com.sun.xml.ws.rx.rm.runtime.sequence.persistent.PersistenceException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.cinestar.entities.Function;
import uy.com.cinestar.entities.Ticket;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class TicketPersistenceBean {

    @PersistenceContext
    EntityManager em;

    @EJB
    private FunctionPersistenceBean functionPersistence;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void buyTicket(Long id, User user) throws CinestarException {
        try {
            Ticket ticket = getTicket(id);
            ticket.setAvailable(false);
            ticket.setUser(user);
            em.merge(ticket);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al comprar el ticket: " + id
                    + ". Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al comprar el ticket: " + id
                    + ". Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public List<Ticket> getFunctionTickets(Long functionId) throws CinestarException {
        try {
            Function function = functionPersistence.getFunction(functionId);
            return function.getTickets();
        } catch (CinestarException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener los "
                    + "tickets de una funcion.", ex);
        }
    }

    public Ticket getTicket(Long id) throws CinestarException {
        try {
            return em.find(Ticket.class, id);
        } catch (Exception ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener el ticket.", ex);
        }
    }

}
