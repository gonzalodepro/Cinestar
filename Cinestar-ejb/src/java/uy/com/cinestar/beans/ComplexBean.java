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
import uy.com.cinestar.domain.Complex;
import uy.com.cinestar.domain.Movie;
import uy.com.cinestar.persistence.ComplexPersistenceBean;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class ComplexBean {

    @EJB
    private ComplexPersistenceBean complexPersistence;
    
    public List<Complex> getAllComplex(){
        return complexPersistence.getAllComplex();
    }
    
    public Complex getComplex(Long id){
        return complexPersistence.getComplex(id);
    }
    
    public List<Movie> getComplexBillboard(Long id){
        return complexPersistence.getComplexBillboard(id);
    }
    
}
