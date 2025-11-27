package faz.api.svrp.dtos;

public class ClientDto {
    private String name;
    private String surname;
    private int documentNumber;
    private String birthdate;
    private String address;
    private String email;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
