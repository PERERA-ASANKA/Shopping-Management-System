import java.io.Serializable;

public abstract class Product implements Serializable {
    private String product_id; // assign product_id
    private String product_name; //assign product_name
    private double product_price; //assign product_price
    private int product_available_items;  //assign product_available_items

    public Product(String product_id,String product_name,double product_price,int product_available_items){      // Create constructor
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_available_items = product_available_items;
    }

    public String getProduct_id(){
        return product_id;
    }           // create get method for product_id
    public void setProduct_id(String product_id){
        this.product_id = product_id;
    }   //create set method for product_id
    public String getProduct_name(){
        return product_name;
    }       // create get method for product_name
    public void setProduct_name(String product_name){ this.product_name = product_name; }    // create set method for product_name
    public double getProduct_price(){
        return product_price;
    }         // create get method for product_price
    public void setProduct_price(double product_price){ this.product_price = product_price; }     // create set method for product_price
    public int getProduct_available_items(){ return product_available_items; }   // create get method for product_available items
    public void setProduct_available_items(int product_available_items){
        this.product_available_items = product_available_items;         // create set method for product_available items
    }

    public abstract String getInfoProduct();      // create get method for details of product
}
