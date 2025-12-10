package faz.api.svrp.models;

public class Discount {

    public static float percentageDiscount;
    public static float iva;

    public static float calculateDiscount(float price) {
        float value = (float) Math.round(price * (percentageDiscount + iva / 100.00));
        return value;
    }
}
