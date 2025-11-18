package faz.api.svrp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TourPackages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String accommodation;
    private String entryDate;
    private String departureDate;
    private String origin;
    private String destiny;
}
