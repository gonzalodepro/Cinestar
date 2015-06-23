
package uy.com.cinestar.persistence;

import com.sun.xml.ws.rx.rm.runtime.sequence.persistent.PersistenceException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import uy.com.cinestar.domain.Complex;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.exceptions.CinestarException;
import uy.com.cinestar.exceptions.DataAccesGenericException;
import uy.com.cinestar.exceptions.NoDataException;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class ComplexPersistenceBean {

    @PersistenceContext
    EntityManager em;

    public void addComplex(Complex u) throws CinestarException {
        try {
            em.persist(u);
        } catch (javax.persistence.EntityExistsException ex) {
            throw new EntityExistsException("Error! Ya existe un complejo con ese id en la base de datos.", ex);
        } catch (javax.persistence.PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al persistir los datos del "
                    + "complejo. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al intentar persistir "
                    + "los datos del complejo. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public List<Complex> getAllComplex() throws CinestarException {
        try {
            Query query = em.createQuery("SELECT c from Complex as c");
            return query.getResultList();
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener los complejos. "
                    + "Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener los complejos. "
                    + "Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public Complex getComplex(Long id) throws CinestarException {
        try {
            Query query = em.createQuery("SELECT c from Complex as c WHERE c.id=:id");
            query.setParameter("id", id);
            return (Complex) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new NoDataException("Error! No existe un complejo con el id: " + id + " en el sistema.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener los datos "
                    + "del complejo. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener los "
                    + "datos del complejo. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public List<Movie> getComplexBillboard(Long id) throws CinestarException {
        try {
            Complex theComplex = (Complex) em.createQuery("SELECT c from Complex as c WHERE c.id=:id")
                    .setParameter("id", id).getSingleResult();
            return theComplex.getBillboard();
        } catch (NoResultException ex) {
            throw new NoDataException("Error! No existe un complejo con el id: " + id + " en el sistema.", ex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al obtener los datos del "
                    + "complejo. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al obtener los datos "
                    + "del complejo. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

    public void updateComplex(Complex complex) throws CinestarException {
        try {
            em.merge(complex);
        } catch (PersistenceException ex) {
            throw new DataAccesGenericException("Disculpe! Ocurrio un error al modificar los datos de la "
                    + "pelicula. Puede que el id sea incorrecto. Intente nuevamente", ex);
        } catch (Exception ex) {
            throw new CinestarException("Disculpe! Ocurrio un error en el sistema al modificar los datos "
                    + "de la pelicula. Intente nuevamente. Si el error persiste contactese con soporte.", ex);
        }
    }

}
