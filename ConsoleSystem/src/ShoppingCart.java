import java.util.ArrayList;

public class ShoppingCart{
    private ArrayList<Product> list_of_products_items = new ArrayList<>();      // create an array

    public void addProduct(Product product){
        list_of_products_items.add(product);   // create addProduct method
    }
    public void removeProduct(Product product){
        list_of_products_items.remove(product);
    }                                                              //create removeProduct method
    public double calculateTotal(){
        double total_cost=0;
        for (Product product:list_of_products_items){
            total_cost += product.getProduct_price();              // calculate total cost
        }
        return total_cost;
    }
}
