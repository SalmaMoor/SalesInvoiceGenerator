/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.controller;

import com.sales.model.InvoiceHeader;
import com.sales.model.LinesTableModel;
import com.sales.view.InvoiceForm;
import com.sales.view.LineForm;
import com.sales.view.MainForm;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author SURE
 */
public class TablesActions implements ListSelectionListener{
    private MainForm mainForm;
    private InvoiceForm invoiceForm;
    private LineForm lineForm;

    public TablesActions(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        int selectedIndex = mainForm.getInvoiceTable().getSelectedRow();
        if (selectedIndex > -1) {
            System.out.println("You have selected row: " + selectedIndex);
            InvoiceHeader selectedInvoice = mainForm.getInvoicesArray().get(selectedIndex);
            mainForm.getInvoiceNumLabel().setText("" + selectedInvoice.getInvoiceNumber());
            mainForm.getInvoiceDateLabel().setText(selectedInvoice.getInvoiceDate());
            mainForm.getCustomerNameLabel().setText(selectedInvoice.getCustomer());
            mainForm.getInvoiceTotalLabel().setText("" + selectedInvoice.getInvoiceTotal());
            LinesTableModel linesTblMdl = new LinesTableModel(selectedInvoice.getLines());
            mainForm.getLineTable().setModel(linesTblMdl);
            linesTblMdl.fireTableDataChanged();
        }
    }
}
