public class Meals {
    private int ID;
    private float price;
    private String name;

    public Meals(int ID, String name,  float price) {
        this.ID = ID;
        this.price = price;
        this.name = name;
    }

    public Meals() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
