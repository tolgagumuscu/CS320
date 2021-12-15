public class FastFoods {

    private int ID, stock;
    private float price;
    private String name , imagePath;

    public FastFoods(int ID,  String name, float price, String imagePath, int stock) {
        this.ID = ID;
        this.price = price;
        this.name = name;
        this.imagePath = imagePath;
        this.stock = stock;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
