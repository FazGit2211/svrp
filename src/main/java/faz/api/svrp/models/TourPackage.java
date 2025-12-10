package faz.api.svrp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private String originCity;
    private String destinyCity;
    private String typeTransport;
    private String date;

    @OneToMany(mappedBy = "tourPackage")
    @JsonIgnore
    private List<Passage> passages;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    public TourPackage(double price, String originCity, String destinyCity, String typeTransport, String date) {
        this.price = price;
        this.originCity = originCity;
        this.destinyCity = destinyCity;
        this.typeTransport = typeTransport;
        this.date = date;
        this.passages = new ArrayList<>();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public void setDestinyCity(String destinyCity) {
        this.destinyCity = destinyCity;
    }

    public void setTypeTransport(String typeTransport) {
        this.typeTransport = typeTransport;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public List<Passage> getPassages() {
        return passages;
    }
}
