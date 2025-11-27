package faz.api.svrp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TourPackage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private String originCity;
    private String destinyCity;
    private String typeTransport;

    @OneToMany(mappedBy = "tourPackage")
    @JsonIgnore
    private List<Passage> passages;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    public TourPackage(double price, String originCity, String destinyCity, String typeTransport) {
        this.price = price;
        this.originCity = originCity;
        this.destinyCity = destinyCity;
        this.typeTransport = typeTransport;
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
}
