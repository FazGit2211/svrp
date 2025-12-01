package faz.api.svrp.models;

public abstract class Discount {

    public double price;
    public double iva;
    public abstract double calculateDiscount();
}
