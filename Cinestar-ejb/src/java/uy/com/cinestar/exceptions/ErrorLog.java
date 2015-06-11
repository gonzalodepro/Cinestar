/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.exceptions;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import uy.com.cinestar.generics.Enums.ExceptionType;

/**
 *
 * @author Gonza
 */
@Entity
public class ErrorLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date occurredDate;
    
    @Enumerated(EnumType.STRING)
    ExceptionType exceptionType;

    public ErrorLog() {
    }

    public ErrorLog(String description) {
        this.description = description;
        this.occurredDate = new Date();
    }

    

    public String getDescription() {
        return description;
    }

    public Date getOccurredDate() {
        return occurredDate;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrorLog)) {
            return false;
        }
        ErrorLog other = (ErrorLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uy.com.cinestar.exceptions.ErrorLog[ id=" + id + " ]";
    }
    
}
