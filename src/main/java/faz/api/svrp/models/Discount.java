package faz.api.svrp.models;

public class Discount {

    public static double percentageDiscount;
    public static double iva;

    public static double calculateDiscount(double price) {
        return price * (percentageDiscount + iva / 100);
    }
}
