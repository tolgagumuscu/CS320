public class Computers {
    private int id;
    private String computerName, startTime, finishTime;
    private boolean isEmpty;
    private double payment;

    public Computers(int id, String computerName, boolean isEmpty, String startTime, String finishTime, double payment) {
        this.id = id;
        this.computerName = computerName;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.isEmpty = isEmpty;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
