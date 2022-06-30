package orders;

public enum OrderStatus {

    PENDING("Pending"),
    SHIPPED("Shipped"),
    DELIVERED("DElIVERED");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


}
