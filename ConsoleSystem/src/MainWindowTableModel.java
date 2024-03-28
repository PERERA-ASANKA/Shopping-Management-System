import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainWindowTableModel extends AbstractTableModel{         // create an own table

    private final String[] columnHeaders = {"Product ID", "Name", "Category", "Price($)", "Info"};  // create an array for column names, and also it cannot be changeable.
    private ArrayList<Product> list_of_products;

    public MainWindowTableModel(ArrayList<Product> list_of_products){          // create a constructor.
        this.list_of_products = list_of_products;
        sortDataById();
    }

    @Override
    public int getRowCount() {                                  // to get the length of the array.
        return list_of_products.size();
    }

    @Override
    public int getColumnCount() {                              // to get the length of the columns array.
        return columnHeaders.length;
    }

    @Override                                                    // to get values at each cell.
    public Object getValueAt(int row_number, int column_number) {
        if (column_number == 0) {
            return list_of_products.get(row_number).getProduct_id();
        } else if (column_number == 1) {
            return list_of_products.get(row_number).getProduct_name();
        } else if (column_number == 2) {
            return list_of_products.get(row_number) instanceof Electronics ? "Electronics" : "Clothing";
        } else if (column_number == 3) {
            return list_of_products.get(row_number).getProduct_price();
        } else if (column_number == 4) {

            if (list_of_products.get(row_number) instanceof Electronics) {
                return ((Electronics) list_of_products.get(row_number)).getElectronics_brand() + ", " + ((Electronics) list_of_products.get(row_number)).getWarranty_period() + " year(s) warranty";
            }
            if (list_of_products.get(row_number) instanceof Clothing) {
                return ((Clothing) list_of_products.get(row_number)).getCloth_size() + ", " + ((Clothing) list_of_products.get(row_number)).getCloth_color();
            }

        } else {
            return null;
        }
        return null;
    }



    @Override
    public String getColumnName(int col){
        return columnHeaders[col];
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Double.class;
        } else if (columnIndex == 4) {
            return Integer.class;
        } else {
            return String.class;
        }
    }
    public void sortDataById() {
        Collections.sort(list_of_products, Comparator.comparing(Product::getProduct_id));
        fireTableDataChanged();
    }
}
