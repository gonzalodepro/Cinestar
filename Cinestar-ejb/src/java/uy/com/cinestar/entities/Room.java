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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Gonza
 */
@Entity
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int number;

    private String description;

    @OneToMany(cascade = ALL)
    private List<Seat> seats;

    public Room() {
        seats = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                Seat s = new Seat(i, j);
                seats.add(s);
            }
        }
    }

    public Room(int number, String description) {
        this.number = number;
        this.description = description;
        seats = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                Seat s = new Seat(i, j);
                seats.add(s);
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id != null ? id.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sala " + number + " - " + description;
    }

}
