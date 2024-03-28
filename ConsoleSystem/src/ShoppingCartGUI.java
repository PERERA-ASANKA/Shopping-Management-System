import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShoppingCartGUI extends JFrame implements ActionListener {
    JTable table_2;      // assign table
    JLabel label_total;
    JLabel label_firstDiscount;
    JLabel label_secondDiscount;   // assign labels
    JLabel label_finalTotal;

    private  boolean is_first_purchase = true;
    private ArrayList<Product> selected_products;
    private ArrayList<String> categories;              // create array lists
    public ShoppingCartGUI(ArrayList<Product> selected_products){
        this.selected_products = selected_products;
        this.categories = new ArrayList<>();
        initialize_2();
        add_toCart();
    }
    public void initialize_2(){
        JFrame frame_2 = new JFrame();         // create a frame
        frame_2.setTitle("Shopping Cart");
        frame_2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //close frame when click on close panel
        frame_2.setSize(700,500);
        frame_2.setLocationRelativeTo(null);

        JPanel first_panel = new JPanel();
        first_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        first_panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        String[] columnHeaders_2 = {"Product","Quantity","Price"};                                   // create a table
        DefaultTableModel table_cartModel = new DefaultTableModel(null,columnHeaders_2);
        table_2 = new JTable(table_cartModel);
        table_2.setGridColor(Color.BLACK);
        first_panel.add(table_2);
        JScrollPane cartScrollPane = new JScrollPane(table_2);
        cartScrollPane.setPreferredSize(new Dimension(600, 200));
        first_panel.add(cartScrollPane);
        frame_2.add(first_panel,BorderLayout.NORTH);

        JPanel second_panel = new JPanel();                                                               // create labels for description and show discount and total prices'
        second_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        second_panel.setLayout(new GridLayout(0,1));
        label_total = new JLabel("Total: ");
        label_total.setBorder(BorderFactory.createEmptyBorder(0,270,0,0));
        second_panel.add(label_total);
        label_firstDiscount = new JLabel("First Purchase Discount(10%): ");
        label_firstDiscount.setBorder(BorderFactory.createEmptyBorder(0,150,0,0));
        second_panel.add(label_firstDiscount);
        label_secondDiscount = new JLabel("Three Items in same category Discount(20%): ");
        label_secondDiscount.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
        second_panel.add(label_secondDiscount);
        label_finalTotal = new JLabel("Final Total: ");
        label_finalTotal.setBorder(BorderFactory.createEmptyBorder(0,240,0,0));
        second_panel.add(label_finalTotal);
        frame_2.add(second_panel,BorderLayout.CENTER);

        JPanel third_panel = new JPanel();
        third_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        third_panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        JButton paymentButton = new JButton("Proceed Payment");
        paymentButton.addActionListener(this);
        third_panel.add(paymentButton);                                               // create cancel and proceed payment  buttons
        JButton cancelPaymentButton = new JButton("Cancel Payment");
        cancelPaymentButton.addActionListener(this);
        third_panel.add(cancelPaymentButton);
        frame_2.add(third_panel,BorderLayout.SOUTH);

        frame_2.setVisible(true);

    }
    public void add_toCart() {
        double total_before_discount =0;
        for (Product product : selected_products) {
            DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
            int rowIndex = findProductRowIndex(product);
            if (rowIndex != -1) {
                int value_of_currentQuantity = (int) tableModel.getValueAt(rowIndex, 1);      // when add same product calculate them in quantity
                double value_of_currentTotalQuantityPrice = (double) tableModel.getValueAt(rowIndex, 2);
                tableModel.setValueAt(value_of_currentQuantity + 1, rowIndex, 1);
                tableModel.setValueAt(value_of_currentTotalQuantityPrice + product.getProduct_price(), rowIndex, 2);
            } else {
                tableModel.addRow(new Object[]{(product.getProduct_id()+","+product.getProduct_name()), 1, product.getProduct_price()});
            }
            total_before_discount += product.getProduct_price();
            String category = String.valueOf(selected_products.getClass());
            if (!categories.contains(category)) {         // get total before the discounts
                categories.add(category);
            }
        }
        updateTotal_label();
        double discountAmount = 0;
        double discount_2_amount = 0;
        if (is_first_purchase) {
            discountAmount = 0.1 * total_before_discount;
            label_firstDiscount.setText("First Purchase Discount: -$" + String.format("%.2f", discountAmount));
            is_first_purchase = false;
        }
        if (secondDiscount()) {
            discount_2_amount += 0.2 * total_before_discount;
            label_secondDiscount.setText("Three Items in same category Discount(20%): -$" + String.format("%.2f", discount_2_amount));
        }
        double final_total = total_before_discount - discountAmount - discount_2_amount;
        label_finalTotal.setText("Final Total: $" + String.format("%.2f", final_total));
    }
    private int findProductRowIndex(Product product) {
        DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String productName = (String) tableModel.getValueAt(i, 0);
            if (productName.equals(product.getProduct_name())) {
                return i;
            }
        }
        return -1;
    }
    private void updateTotal_label() {
        DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
        double total_price = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total_price += (double) tableModel.getValueAt(i, 2);
        }
        label_total.setText("Total: $" + String.format("%.2f", total_price));
    }
    private boolean secondDiscount() {
        for (String category : categories) {
            int count = countItemsInCategory(category);
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }
    private int countItemsInCategory(String category) {
        int count = 0;
        for (Product product : selected_products) {
            if (String.valueOf(selected_products.getClass()).equals(category)) {
                count++;
            }
        }
        return count;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cancel Payment")) {
            clearShoppingCart();
        } else if (e.getActionCommand().equals("Proceed Payment")) {
            clearShoppingCart();
        }
    }
    private void clearShoppingCart() {     // create a method to clear shopping cart
        DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
        tableModel.setRowCount(0);
        updateTotal_label();
        label_firstDiscount.setText("First Purchase Discount: ");
        label_secondDiscount.setText("Three Items in same category Discount(20%): ");
        label_finalTotal.setText("Final Total: ");
    }
}
