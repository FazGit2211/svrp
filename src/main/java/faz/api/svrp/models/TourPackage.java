package faz.api.svrp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @OneToMany
    private List<City> cities;
}
