package uy.com.cinestar.sb;

import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import uy.com.cinestar.entities.Ticket;
import uy.com.cinestar.entities.User;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.LoginException;
import uy.com.cinestar.persistence.TicketPersistenceBean;


@Stateless
@LocalBean
public class TicketBean {

    @EJB
    private TicketPersistenceBean persistence;

    
    @Inject
    private SystemBean system;
    
    public List<Ticket> getFunctionTickets(Long functionId) throws CinestarException {
        return persistence.getFunctionTickets(functionId);
    }

    public void buyTickets(UUID token, List<Long> ticketsId) throws CinestarException {
        try{
            boolean allTicketsAvailable = true;
            for (Long ticketsId1 : ticketsId) {
                Ticket theTicket = persistence.getTicker(ticketsId1);
                if (!theTicket.isAvailable()){
                    allTicketsAvailable = false;
                    break;
                }
            }
            if (!allTicketsAvailable)
                throw new CinestarException("Disculpe! Al menos uno de los tickets que desea "
                        + "comprar ya no esta disponible!",null);
            else{
                User buyer = system.IsCorrectToken(token);
                if (buyer==null){
                    throw new LoginException("Disculpe! El usuario comprador no esta logueado. "
                            + "Puede que haya expirado su sesion. Intente nuevamente!",null);
                }   
                for (Long ticketsId1 : ticketsId) {
                    persistence.buyTicket(ticketsId1, buyer);
                }
            }
        }catch (CinestarException ex){
            throw ex;
        }
    }
}
