/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import uy.com.cinestar.domain.Ticket;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.persistence.TicketPersistenceBean;


/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class TicketBean {

    @EJB
    private TicketPersistenceBean persistence;
    
    public List<Ticket> getFunctionTickets(Long functionId) throws CinestarException{
        return persistence.getFunctionTickets(functionId);
    }
}
