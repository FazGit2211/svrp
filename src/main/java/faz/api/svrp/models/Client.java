package faz.api.svrp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Person {
    @OneToMany(mappedBy = "client")
    private List<Passage> passages;

    public Client(String name,String surname,int document,String birthdate,String address,String email){
        this.name = name;
        this.surname = surname;
        this.documentNumber = document;
        this.birthdate = birthdate;
        this.address = address;
        this.email = email;
    }

    public void setName(String name){
        if (!name.trim().isBlank()){
            this.name = name;
        }
    }

    public void setSurname(String surname){
        if (!surname.trim().isBlank()){
            this.surname = surname;
        }
    }

    public void setDocumentNumber(int document){
        if (document > 0){
            this.documentNumber = document;
        }
    }

    public void setBirthdate(String birthdate){
        if (!birthdate.trim().isBlank()){
            this.birthdate = birthdate;
        }
    }

    public void setAddress(String address){
        if (!address.trim().isBlank()){
            this.address = address;
        }
    }

    public void setEmail(String email){
        if (!email.trim().isBlank()){
            this.email = email;
        }
    }
}
