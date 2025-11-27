package faz.api.svrp.dtos;

public class TourPackageDto {
    private double price;
    private String originCity;
    private String destinyCity;
    private String typeTransport;
    private int enterpriseId;

    public double getPrice() {
        return price;
    }

    public String getOriginCity() {
        return originCity;
    }

    public String getDestinyCity() {
        return destinyCity;
    }

    public String getTypeTransport() {
        return typeTransport;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }
}
