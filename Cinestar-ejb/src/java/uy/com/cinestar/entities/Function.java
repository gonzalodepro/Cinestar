/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Function implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(nullable = false)
    private Room room;

    @JoinColumn(nullable = false)
    private Movie movie;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @OneToMany
    private List<Ticket> tickets;

    private double price;

    public Function() {
        tickets = new ArrayList<>();
    }

    public Function(Room room, Movie movie, Date startDate, double price) {
        this.room = room;
        this.movie = movie;
        this.startDate = startDate;
        this.price = price;
        this.tickets = new ArrayList<>();
        for (int seatNumber = 0; seatNumber < room.getSeats().size(); seatNumber++) {
            Ticket newTicket = new Ticket(room.getSeats().get(seatNumber).getColumn(), 
                    room.getSeats().get(seatNumber).getRow());
            this.tickets.add(newTicket);
        }

    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUid() {
        return serialVersionUID;
    }

    public Room getRoom() {
        return room;
    }

    public Movie getMovie() {
        return movie;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setRoom(Room room) {
        this.room = room;
//        this.tickets = new ArrayList<>();
//        for (int seatNumber=0; seatNumber<room.getSeats().size();seatNumber++){
//            Ticket newTicket = new Ticket(room.getSeats().get(seatNumber).getColumn(),
        //room.getSeats().get(seatNumber).getRow());
//            this.tickets.add(newTicket);
//        }
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += id != null ? id.hashCode() : 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
