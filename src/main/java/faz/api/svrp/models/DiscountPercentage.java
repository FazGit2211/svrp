package faz.api.svrp.models;

public class DiscountPercentage extends Discount{

    @Override
    public double calculateDiscount(double prise, double discount,double iva) {
        double result = prise * (1 + iva /100);
        return result;
    }
}
