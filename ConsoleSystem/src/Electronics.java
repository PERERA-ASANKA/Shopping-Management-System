import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    private String electronics_brand;    // assign electronics brand
    private int warranty_period;        //assign electronics warranty period

    public Electronics(String product_id,String product_name,double product_price,int product_available_items,String electronics_brand,int warranty_period){
        super(product_id,product_name,product_price,product_available_items);
        this.electronics_brand = electronics_brand;                //create constructor
        this.warranty_period = warranty_period;
    }

    public String getElectronics_brand(){
        return electronics_brand;
    }        //create get method for electronics brand
    public void setElectronics_brand(String electronics_brand){
        this.electronics_brand = electronics_brand;
    }    //create set method for electronics brand
    public int getWarranty_period(){
        return warranty_period;
    }     //create get method for warranty period
    public void setWarranty_period(int warranty_period){
        this.warranty_period = warranty_period;
    }     //create set method for warranty period

    @Override
    public String getInfoProduct() {                  //create get method for details of Electronics
        return "Product Id of Electronics: "+getProduct_id()+", Name: "+getProduct_name()+", Price: "+getProduct_price()+", Available Items: "+getProduct_available_items()+", Brand: "+electronics_brand+", Warranty Period: "+warranty_period;
    }

}
