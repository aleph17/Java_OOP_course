package streams;
import java.util.ArrayList;
import java.util.List;

class Product {
    private String name;
    private List<Integer> orders = new ArrayList<>();
    private String productType;

    public Product(String name, String productType) {
        this.name = name;
        this.productType=productType;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return productType;
    }


    public List<Integer> getOrders() {
        return orders;
    }

    public int getOrderCount() {
        return orders.size();
    }

    public void placeOrder(Integer nPieces){
        this.orders.add(nPieces);
        
    }
}
