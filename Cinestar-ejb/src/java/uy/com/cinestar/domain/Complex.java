/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;



@Entity
public class Complex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade=ALL)
    private List<Room> rooms;
    
    @OneToMany(cascade=ALL)
    private List<Function> functions;
    
    @Column(nullable=false)
    private String name;

    public Complex() {
        rooms = new ArrayList<>();
        functions = new ArrayList<>();
    }

    
    
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Function> getFunctions() {
        return functions;
    }
    
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Complex)) {
            return false;
        }
        Complex other = (Complex) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uy.com.cinestar.domain.ComplexEntity[ id=" + id + " ]";
    }
    
}
