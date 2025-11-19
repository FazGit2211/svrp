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
public class TourPackage extends Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalPrice;
    private String accommodation;
    private String entryDate;
    private String departureDate;
    private String originCity;
    private String destinyCity;

    @Override
    public void addIva(int iva) {
        double result = this.totalPrice * (1 + iva /100);
        this.totalPrice = result;
    }
}
