package faz.api.svrp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Basic
    public String name;
    public String surname;
    public int documentNumber;
    public String birthdate;
    public String address;
    public String email;
}
