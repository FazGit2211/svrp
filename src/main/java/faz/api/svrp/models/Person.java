package faz.api.svrp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int documentNumber;
    private String birthdate;
    private String address;
    private String email;

    public Person(String paramName,String paramSurname,int paramDocument,String paramBirthdate,String paramAddress,String paramEmail){}
}
