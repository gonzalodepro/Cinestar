/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.beans;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.cinestar.domain.PruebaEntity;


@Stateless
@LocalBean
public class PruebaBean {

    @PersistenceContext
    EntityManager em;
    
    public void crearAuto(String matricula, Integer año) throws Exception{
        if (matricula == null)
            throw new Exception("Matricula nula");
         
        int index;
        index = em.createQuery("select a FROM PruebaEntity a where a.matricula=:mat").setParameter("mat", matricula).getFirstResult();
        //esta cagada siempre devuelve 0!
        
        if (index>0)
            throw new Exception("Matricula ya existe");
        
        PruebaEntity auto = new PruebaEntity();
        
        auto.setAño(año);
        auto.setMatricula(matricula);
        
        em.persist(auto);
        
        
        
            
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
