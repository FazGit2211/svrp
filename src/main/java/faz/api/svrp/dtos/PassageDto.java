package faz.api.svrp.dtos;

public class PassageDto {
    private String date;
    private String ticketNumber;
    private String seatNumber;
    private float totalPrice;
    private int clientId;
    private int tourpackageId;

    public String getDate() {
        return date;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getClientId() {
        return clientId;
    }

    public int getTourpackageId() {
        return tourpackageId;
    }
}
