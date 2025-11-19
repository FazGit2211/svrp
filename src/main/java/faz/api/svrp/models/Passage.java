package faz.api.svrp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Passages")
public class Passage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String originCity;
    private String destinyCity;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private String ticketNumber;
    private String seatNumber;
    private double totalPrice;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client  client;

}
