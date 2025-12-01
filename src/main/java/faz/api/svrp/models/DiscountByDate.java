package faz.api.svrp.models;

public class DiscountByDate extends Discount{

    private String date;

    @Override
    public double calculateDiscount() {
        if (date.trim().equalsIgnoreCase("24-12-2025")){
            this.price = this.price * (50 + this.iva /100);
        }
        return this.price;
    }
}
