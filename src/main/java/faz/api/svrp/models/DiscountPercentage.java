package faz.api.svrp.models;

public class DiscountPercentage extends Discount{

    private double percentageDiscount;
    @Override
    public double calculateDiscount() {
        return this.price * (this.percentageDiscount + this.iva /100);
    }

    public void setPercentageDiscount(double percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }


}
