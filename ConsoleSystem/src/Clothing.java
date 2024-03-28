import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    private String cloth_size;   //assign cloth size
    private String cloth_color;        // assign cloth color

    public Clothing(String product_id,String product_name,double product_price,int product_available_items,String cloth_size,String cloth_color){
        super(product_id,product_name,product_price,product_available_items);
        this.cloth_size = cloth_size;                                                   //Create Constructor
        this.cloth_color = cloth_color;
    }

    public String getCloth_size(){
        return cloth_size;
    }        // create get method for cloth size
    public void setCloth_size(String cloth_size){
        this.cloth_size = cloth_size;
    }    // create set method for cloth size
    public String getCloth_color(){
        return cloth_color;
    }        // create get method for cloth color
    public void setCloth_color(String cloth_color){
        this.cloth_color = cloth_color;
    }     // create set method for cloth color

    @Override
    public String getInfoProduct() {             //create get method for details of clothing
        return "Product Id of Clothing: "+getProduct_id()+", Name: "+getProduct_name()+", Price: "+getProduct_price()+", Available Items: "+getProduct_available_items()+", Cloth Color: "+cloth_color+", Cloth Size: "+cloth_size;
    }
}
