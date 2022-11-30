
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoicesTableModel extends AbstractTableModel {
    private ArrayList<InvoiceHeader> invoicesArray;
    private String[] columns = {"No.", "Date", "Customer", "Total"};
    
    public InvoicesTableModel(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    
    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoice = invoicesArray.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return invoice.getInvoiceNumber();
            case 1: return invoice.getInvoiceDate();
            case 2: return invoice.getCustomer();
            case 3: return invoice.getInvoiceTotal();
            default : return "";
        }
    }
}
