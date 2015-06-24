
package uy.com.cinestar.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;

/**
 *
 * @author Gonza
 */
@Entity
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int _row;

    @Column(nullable = false)
    private int _column;

    @Column(nullable = false)
    private boolean available;

    @ManyToOne(optional = true)
    private User user;
    
    @ManyToOne
    private Function function;
    
    public Ticket() {
        this.available = true;
    }

    public Ticket(int row, int column, Function fun) {
        this._row = row;
        this._column = column;
        this.function = fun;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRow() {
        return _row;
    }

    public int getColumn() {
        return _column;
    }

    public void setRow(int row) {
        this._row = row;
    }

    public void setColumn(int column) {
        this._column = column;
    }

    public User getUser() {
        return user;
    }

    public Function getFunction() {
        return function;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id != null ? id.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uy.com.cinestar.domain.Ticket[ id=" + id + " ]";
    }

}
