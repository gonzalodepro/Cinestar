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
import uy.com.cinestar.exceptions.EntityExistsException;
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

    public String buyTickets(UUID token, List<Long> ticketsId) throws CinestarException {
        try {
            String emailText = "";
            boolean allTicketsAvailable = true;
            boolean allTicketsIdCorrects = true;
            for (Long ticketsId1 : ticketsId) {
                Ticket theTicket = persistence.getTicket(ticketsId1);
                if (theTicket==null){
                    allTicketsIdCorrects=false;
                    break;
                }
                if (!theTicket.isAvailable()) {
                    allTicketsAvailable = false;
                    break;
                }
            }
            if (!allTicketsIdCorrects){
                throw new EntityExistsException("Error! Al menos uno de los id de los tickets que quiere "
                        + "comprar es incorrecto.",null);
            }
            if (!allTicketsAvailable) {
                throw new CinestarException("Disculpe! Al menos uno de los tickets que desea "
                        + "comprar ya no esta disponible!", null);
            } else {
                double totalPrice = 0;
                User buyer = system.IsCorrectToken(token);
                if (buyer == null) {
                    throw new LoginException("Disculpe! El usuario comprador no esta logueado. "
                            + "Puede que haya expirado su sesion. Intente nuevamente!", null);
                }
                emailText += "Estimado/a supervisor/a. <br><br>     Confirmamos la compra por parte del cliente " + buyer.getNick() + " del/os ticket/s:<br>";
                Ticket ticket;
                for (Long ticketsId1 : ticketsId) {
                    persistence.buyTicket(ticketsId1, buyer);
                    ticket = persistence.getTicket(ticketsId1);
                    emailText += "<br>-Pelicula " + ticket.getFunction().getMovie().getTitle() + " Fecha: " + ticket.getFunction().getStartDate();
                    emailText += " Sala: " + ticket.getFunction().getRoom().getNumber() + "<br>";
                    emailText += "        Asiento: " + ticket.getRow() + "," + ticket.getColumn() + ".<br>";
                    totalPrice += ticket.getFunction().getPrice();
                }
                emailText += "<br><br><br> Costo total compra " + totalPrice + "$";
            }
            return emailText;
        
        }catch(CinestarException ex){
            throw ex;
        }catch (Exception ex){
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al intentar realizar la compra. Intente nuevamente. "
                    + "Si el error persiste contactese con soporte.",ex);
        }
    }
}
