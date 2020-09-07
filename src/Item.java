//Cliente class

public class Item {
    
    private int id;
    private String name;
    private double price;
    private String obs = null;

    Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void setObs(String obs) {
        this.obs = obs;
    }

    int getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    double getPrice() {
        return this.price;
    }

    String getObs() {
        return this.obs;
    }
}
