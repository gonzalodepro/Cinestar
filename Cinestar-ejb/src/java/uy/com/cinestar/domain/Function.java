/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;


@Entity
public class Function implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Room room;
    
//    @ManyToOne
//    private Room room;
    
    @ManyToOne
    private Movie film;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date startDate;

    public Date getDate() {
        return startDate;
    }

    public void setDate(Date date) {
        this.startDate = date;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public void setRoom(Room room) {
//        this.room = room;
//    }
//    public Room getRoom() {
//        return room;
//    }

    public void setFilm(Movie film) {
        this.film = film;
    }

    public Movie getFilm() {
        return film;
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
        if (!(object instanceof Function)) {
            return false;
        }
        Function other = (Function) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uy.com.cinestar.domain.FuncionEntity[ id=" + id + " ]";
    }
    
}
