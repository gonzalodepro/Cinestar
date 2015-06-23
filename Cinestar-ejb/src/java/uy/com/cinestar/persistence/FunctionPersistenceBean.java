/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.persistence;

import com.sun.xml.ws.rx.rm.runtime.sequence.persistent.PersistenceException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.entities.Complex;
import uy.com.cinestar.entities.Function;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.exceptions.NoDataException;

@Stateless
@LocalBean
public class FunctionPersistenceBean {

    @PersistenceContext
    EntityManager em;

    @EJB
    private ComplexPersistenceBean complexPersistence;

    public List<Function> getAllFunctions() throws CinestarException {
        try {
            Query query = em.createQuery("SELECT f from Function as f");
            return query.getResultList();
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener las "
                    + "funciones. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener "
                    + "las funciones. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public Function getFunction(long id) throws CinestarException {
        try {
            Query query = em.createQuery("SELECT f from Function as f where f.id=:id");
            query.setParameter("id", id);
            return (Function) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new NoDataException("Error! No existe una funcion con el id: " + id + " en el sistema.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener los datos "
                    + "de la fucion. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener "
                    + "los datos de la funcion. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public void addFunction(Function func) throws CinestarException {
        try {
            em.persist(func);
        } catch (javax.persistence.EntityExistsException ex) {
            throw new EntityExistsException("Error! Ya existe una funcion con ese id en la base de datos.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos "
                    + "de la funcion. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al intentar "
                    + "persistir la funcion. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public List<Function> getComplexFunction(Long complexId) throws CinestarException {
        try {
            Complex theComplex = complexPersistence.getComplex(complexId);
            return theComplex.getFunctions();
        } catch (NoDataException ex) {
            throw ex;
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al consultar las funciones del complejo. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al consultar las funciones del complejo. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public boolean buyTicket(Long id, int seatRow, int seatColumn) {
        try {
            //ARMAR QUERY
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
