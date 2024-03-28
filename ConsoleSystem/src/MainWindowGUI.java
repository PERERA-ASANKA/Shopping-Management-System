import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindowGUI extends JFrame implements ActionListener{
    private ArrayList<Product> selected_products = new ArrayList<>();   // create an array
    JComboBox <String> selectionBox;
    JTable table;
    JTextArea detailsArea;
    MainWindowTableModel model;
    public MainWindowGUI(){         // create a constructor
        initialize();
    }
    public void initialize(){
        JFrame frame = new JFrame();
        frame.setTitle("Westminster Shopping Centre");            //create a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel_1.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        JLabel label_1 = new JLabel("Select Product Category");                                  //Create label to add text
        label_1.setBorder(BorderFactory.createEmptyBorder(0,0,0,50));
        leftPanel.add(label_1);

        selectionBox = new JComboBox<>(new String[] {"All","Electronics","Clothing"});        //add a selection box
        selectionBox.addActionListener(this);
        selectionBox.setBorder(BorderFactory.createEmptyBorder(0,50,0,0));
        leftPanel.add(selectionBox);
        panel_1.add(leftPanel,BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,5));
        JButton cartButton = new JButton(" Shopping Cart ");                                 // add shopping cart button
        cartButton.addActionListener(this);
        rightPanel.add(cartButton);
        panel_1.add(rightPanel,BorderLayout.EAST);
        frame.add(panel_1,BorderLayout.NORTH);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel_2.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        frame.add(panel_2,BorderLayout.CENTER);

        table = new JTable();
        model = new MainWindowTableModel(WestminsterShoppingManager.list_of_products);
        table.setModel(model);
        table.setGridColor(Color.BLACK);                                                         // add table from Main Window Table Model
        panel_2.add(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,300));
        panel_2.add(scrollPane);



        JPanel panel_3 = new JPanel();
        panel_3.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        frame.add(panel_3,BorderLayout.SOUTH);

        detailsArea = new JTextArea(12,50);                      //add a text area
        detailsArea.setEditable(false);
        panel_3.add(detailsArea);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    int row_of_select = table.getSelectedRow();
                    if (row_of_select != -1){                                                // add texts in text area
                        detailsArea.setText("");
                        detailsArea.append("\n");
                        detailsArea.append("Selected Product - Details"+" \n");
                        detailsArea.append("\n");
                        detailsArea.append("Product ID: "+table.getValueAt(row_of_select,0)+"\n");
                        detailsArea.append("Category: "+table.getValueAt(row_of_select,2)+"\n");
                        detailsArea.append("Name: "+table.getValueAt(row_of_select,1)+"\n");
                        if (WestminsterShoppingManager.list_of_products.get(row_of_select).getProduct_available_items() < 3){
                            table.setSelectionBackground(Color.RED);
                            detailsArea.append("Items available: "+WestminsterShoppingManager.list_of_products.get(row_of_select).getProduct_available_items()+"\n");

                        }else {
                            table.setSelectionBackground(table.getBackground());
                            detailsArea.append("Items available: " + WestminsterShoppingManager.list_of_products.get(row_of_select).getProduct_available_items() + "\n");

                        }
                        Object selectedProduct = WestminsterShoppingManager.list_of_products.get(row_of_select);
                        if (selectedProduct instanceof Electronics){
                            detailsArea.append("Brand: "+((Electronics)selectedProduct).getElectronics_brand()+"\n");
                            detailsArea.append("Warranty Period: "+((Electronics)selectedProduct).getWarranty_period()+"year(s) warranty"+"\n");
                        }
                        else if (selectedProduct instanceof Clothing){
                            detailsArea.append("Size: "+((Clothing)selectedProduct).getCloth_size()+"\n");
                            detailsArea.append("Color: "+((Clothing)selectedProduct).getCloth_color()+"\n");
                        }
                    }
                }
            }
        });

        JButton addCartButton = new JButton("Add to Shopping Cart");         // create an add to shopping cart button
        addCartButton.addActionListener(this);
        panel_3.add(addCartButton);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==selectionBox){
            String selected_type= (String) selectionBox.getSelectedItem();  // how the selection box work
            if ("All".equals(selected_type)){
                MainWindowTableModel model = new MainWindowTableModel(WestminsterShoppingManager.list_of_products);
                table.setModel(model);
                detailsArea.setText("");
            } else if ("Electronics".equals(selected_type)) {
                ArrayList<Product> list_of_electronics = new ArrayList<>();
                for (Product product : WestminsterShoppingManager.list_of_products) {
                    if (product instanceof Electronics) {
                        list_of_electronics.add(product);
                    }
                }
                MainWindowTableModel model = new MainWindowTableModel(list_of_electronics);
                table.setModel(model);
                detailsArea.setText("");
            } else if ("Clothing".equals(selected_type)) {
                ArrayList<Product> list_of_clothing = new ArrayList<>();
                for (Product product : WestminsterShoppingManager.list_of_products) {
                    if (product instanceof Clothing) {
                        list_of_clothing.add(product);
                    }
                }
                MainWindowTableModel model = new MainWindowTableModel(list_of_clothing);
                table.setModel(model);
                detailsArea.setText("");
            }
        }else if (e.getActionCommand().equals(" Shopping Cart ")) {          // when click on buttons and what happens
            new ShoppingCartGUI(selected_products);
        } else if (e.getActionCommand().equals("Add to Shopping Cart")) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Product selectedProduct = WestminsterShoppingManager.list_of_products.get(selectedRow);
                if (selectedProduct.getProduct_available_items() > 0) {
                    selectedProduct.setProduct_available_items(selectedProduct.getProduct_available_items() - 1);
                    ((MainWindowTableModel) table.getModel()).fireTableDataChanged();
                    selected_products.add(selectedProduct);
                    JOptionPane.showMessageDialog(this, "Item added to cart. Remaining items: " + selectedProduct.getProduct_available_items());
                } else {
                    JOptionPane.showMessageDialog(this, "Sorry, the item is out of stock.");
                }
            }
        }
    }
}

