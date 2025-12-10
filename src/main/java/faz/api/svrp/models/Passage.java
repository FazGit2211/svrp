package faz.api.svrp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Passages")
public class Passage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date;
    private String ticketNumber;
    private String seatNumber;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "tourpackage_id")
    private TourPackage tourPackage;

    public Passage() {
    }

    public Passage(String date, String ticketNumber, String seatNumber, double totalPrice) {
        this.date = date;
        this.ticketNumber = ticketNumber;
        this.seatNumber = seatNumber;
        this.totalPrice = totalPrice;
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Client getClient() {
        return client;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public int getId() {
        return id;
    }
}
