package faz.api.svrp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clients")
@Data
public class Client extends Person {

    public Client(String paramName,String paramSurname,int paramDocument,String paramBirthdate,String paramAddress,String paramEmail){
        super(paramName,paramSurname,paramDocument,paramBirthdate,paramAddress,paramEmail);
    }
}
