
package uy.com.cinestar.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Complex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = ALL)
    private List<Room> rooms;

    @OneToMany(cascade = ALL)
    private List<Function> functions;

    @OneToMany(cascade = ALL)
    private List<Movie> billboard;

    @Column(nullable = false)
    private String name;

    public Complex() {
        rooms = new ArrayList<>();
        functions = new ArrayList<>();
        billboard = new ArrayList<>();
    }

    public List<Movie> getBillboard() {
        return billboard;
    }

    public void setBillboard(List<Movie> billboard) {
        this.billboard = billboard;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public static long getSerialVersionUid() {
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

    public void addMovieToBillboard(Movie movie) {
        this.billboard.add(movie);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id != null ? id.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
