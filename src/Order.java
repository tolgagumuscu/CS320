public class Order {

    private int orderID, orderedPersonID, orderedFoodID, orderedBeverageID;
    private String orderTime;
    private double payment;

    public Order(int orderID, int orderedPersonID, int orderedFoodID, int orderedBeverageID, String orderTime, double payment) {
        this.orderID = orderID;
        this.orderedPersonID = orderedPersonID;
        this.orderedFoodID = orderedFoodID;
        this.orderedBeverageID = orderedBeverageID;
        this.orderTime = orderTime;
        this.payment = payment;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderedPersonID() {
        return orderedPersonID;
    }

    public void setOrderedPersonID(int orderedPersonID) {
        this.orderedPersonID = orderedPersonID;
    }

    public int getOrderedFoodID() {
        return orderedFoodID;
    }

    public void setOrderedFoodID(int orderedFoodID) {
        this.orderedFoodID = orderedFoodID;
    }

    public int getOrderedBeverageID() {
        return orderedBeverageID;
    }

    public void setOrderedBeverageID(int orderedBeverageID) {
        this.orderedBeverageID = orderedBeverageID;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
