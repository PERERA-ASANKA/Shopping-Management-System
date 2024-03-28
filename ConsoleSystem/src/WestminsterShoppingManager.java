import javax.swing.*;
import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager{
    private static final int MAX_PRODUCTS=50;  // create not changeable MAX_PRODUCTS
    public static ArrayList<Product> list_of_products; // create array called list_of_products
    Scanner input_type = new Scanner(System.in);    // get Scanner for inputs
    Scanner input_others = new Scanner(System.in);
    public WestminsterShoppingManager(){
        this.list_of_products = new ArrayList<>();     // create constructor
        loadFromFile();
    }

    @Override
    public void addProduct() {       // create addProduct method
        do {
            try {
                System.out.println("Enter product type name (Electronics/Clothing) : ");
                String type = input_type.nextLine();    // get input as Electronics/Clothing
                if (type.equalsIgnoreCase("electronics") || type.equalsIgnoreCase("clothing")) {   // check the inputs are correct
                    System.out.println("Enter the Product ID: ");
                    String product_id = input_others.next();    // get product id
                    System.out.println("Enter the Product Name: ");
                    String product_name = input_others.next();     // get product name
                    System.out.println("Enter the Product Price: ");
                    double product_price = input_others.nextDouble();     // get product price
                    System.out.println("Enter the Product available items: ");
                    int product_available_items = input_others.nextInt();      // get product available items
                    if (type.equalsIgnoreCase("electronics")){ // check whether it is electronics
                        System.out.println("Enter the Electronics brand: ");   // get electronics brand
                        String electronics_brand = input_others.next();
                        System.out.println("Enter the Electronics warranty period: "); // get warranty period
                        int warranty_period = input_others.nextInt();
                        if (list_of_products.size() < MAX_PRODUCTS){  // check array size
                            Product product = new Electronics(product_id,product_name,product_price,product_available_items,electronics_brand,warranty_period);
                            list_of_products.add(product);                         // added product details to array
                            System.out.println("Successfully added the product.");
                        }
                        else{
                            System.out.println("Reached the maximum limit, So can't add any product.");
                            break;
                        }
                    }
                    else {                  // when input was clothing
                        System.out.println("Enter the Clothing color: ");   // get cloth color
                        String cloth_color = input_others.next();
                        System.out.println("Enter the Clothing size: ");   // get cloth size
                        String cloth_size = input_others.next();
                        if (list_of_products.size() < MAX_PRODUCTS){
                            Product product = new Clothing(product_id,product_name,product_price,product_available_items,cloth_size,cloth_color);
                            list_of_products.add(product);                              // added product details to array
                            System.out.println("Successfully added the product.");
                        }
                        else{
                            System.out.println("Reached the maximum limit, So can't add any product.");
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("Enter the correct product type name.");  // checking correct name
                }
            } catch (Exception e) {
                System.out.println("Invalid Input :"+ e );  // when get errors solve this
            }
        } while (true);
    }

    @Override
    public void removeProduct() {    // create removeProduct method
        System.out.println("Enter the Product ID to remove the Product: ");   // get the id of wanted to remove
        String product_id = input_others.next();
        Iterator<Product> product_iterator= list_of_products.iterator();
        while(product_iterator.hasNext()){
            Product product=product_iterator.next();             // check the product id
            if(product.getProduct_id().equals(product_id)){
                System.out.println("Successfully removed product: "+product.getInfoProduct()); // when get equal one remove the product
                product_iterator.remove();
                return;
            }
        }
        System.out.println("Nothing found called Product Id as "+product_id);
    }

    @Override
    public void printProducts() {   // create method to print products
        Collections.sort(list_of_products, Comparator.comparing(Product::getProduct_id));      //sort the list according to id
        for(Product product: list_of_products){
            if(product instanceof Electronics){
                System.out.println("Electronics: "+ product.getInfoProduct());
            } else if (product instanceof Clothing){                                     //get the full details of both electronics and clothing
                System.out.println("Clothing: "+((Clothing) product).getInfoProduct());
            }
        }
    }

    @Override
    public void saveToFile() {         // Create method to save
        File console_file = new File("system_console.dat");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(console_file);
            ObjectOutputStream stream_of_output = new ObjectOutputStream(fileOutputStream);
            stream_of_output.writeObject(list_of_products);
            stream_of_output.close();
        } catch (Exception e) {
            System.out.println("Have a error" + e);
        }
    }
    @Override
    public void loadFromFile() {   // create method to load the save file
        try (ObjectInputStream stream_of_input = new ObjectInputStream(new FileInputStream("system_console.dat"))) {
            try {
                ArrayList<Product> get_detailsArray = (ArrayList<Product>) stream_of_input.readObject();
                stream_of_input.close();
                list_of_products = get_detailsArray;
            } catch (Exception e) {
                System.out.println("Have a error:"+e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
    public void openGUI(){      // create method to open GUI
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainWindowGUI main_window = new MainWindowGUI();
                }
            });
    }

    public void display_menu(){    // create the display menu
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("--------------------------   MENU   --------------------------");
        System.out.println("--------------------------------------------------------------");
        System.out.println("   1. Add a new product");
        System.out.println("   2. Remove a selected product");
        System.out.println("   3. Print the product list");
        System.out.println("   4. Save to a file");
        System.out.println("   5. Load from file");
        System.out.println("   6. Open the GUI");
        System.out.println("   0. Exit");
        System.out.println();
        System.out.println("--------------------------------------------------------------");
    }
}
