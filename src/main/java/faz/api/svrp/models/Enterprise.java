package faz.api.svrp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Enterprises")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String cuit;

    @OneToMany(mappedBy = "enterprise")
    @JsonIgnore
    private List<TourPackage> tourPackages;

    public Enterprise(String name, String address, String cuit) {
        this.name = name;
        this.address = address;
        this.cuit = cuit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
}
