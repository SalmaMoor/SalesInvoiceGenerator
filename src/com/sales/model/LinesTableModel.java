
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class LinesTableModel extends AbstractTableModel {

    private ArrayList<InvoiceLine> linesArray;
    private String[] columns = {"No.", "Item Name", "Item Price", "Count", "Item Total"};

    public LinesTableModel(ArrayList<InvoiceLine> linesArray) {
        this.linesArray = linesArray;
    }

    public ArrayList<InvoiceLine> getLinesArray() {
        return linesArray;
    }
    
    @Override
    public int getRowCount() {
        return linesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int x) {
        return columns[x];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line = linesArray.get(rowIndex);
        
        switch(columnIndex) {
            case 0: return line.getInvoice().getInvoiceNumber();
            case 1: return line.getItemName();
            case 2: return line.getPrice();
            case 3: return line.getCount();
            case 4: return line.getLineTotal();
            default : return "";
        }
    }
    
}
