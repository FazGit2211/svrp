package faz.api.svrp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "Passages")
public class Passage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String ticketNumber;
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client  client;

    @ManyToOne
    @JoinColumn(name = "tourpackage_id")
    private TourPackage tourPackage;

    public Passage(String date, String ticketNumber, String seatNumber) {
        this.date = date;
        this.ticketNumber = ticketNumber;
        this.seatNumber = seatNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }
}
