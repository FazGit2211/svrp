package faz.api.svrp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends Person {
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Passage> passages;

    public Client() {
    }

    public Client(String name, String surname, int document, String birthdate, String address, String email) {
        this.name = name;
        this.surname = surname;
        this.documentNumber = document;
        this.birthdate = birthdate;
        this.address = address;
        this.email = email;
        this.passages = new ArrayList<>();
    }

    public void setName(String name) {
        if (!name.trim().isBlank()) {
            this.name = name;
        }
    }

    public void setSurname(String surname) {
        if (!surname.trim().isBlank()) {
            this.surname = surname;
        }
    }

    public void setDocumentNumber(int document) {
        if (document > 0) {
            this.documentNumber = document;
        }
    }

    public void setBirthdate(String birthdate) {
        if (!birthdate.trim().isBlank()) {
            this.birthdate = birthdate;
        }
    }

    public void setAddress(String address) {
        if (!address.trim().isBlank()) {
            this.address = address;
        }
    }

    public void setEmail(String email) {
        if (!email.trim().isBlank()) {
            this.email = email;
        }
    }

    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }
}
