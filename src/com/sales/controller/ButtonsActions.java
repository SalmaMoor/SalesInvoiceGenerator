/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.controller;

import com.sales.model.InvoiceHeader;
import com.sales.model.InvoiceLine;
import com.sales.model.LinesTableModel;
import com.sales.view.InvoiceForm;
import com.sales.view.LineForm;
import com.sales.view.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author SURE
 */
public class ButtonsActions implements ActionListener{
    private MainForm mainForm;
    private InvoiceForm invoiceForm;
    private LineForm lineForm;

    public ButtonsActions(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            //--------------- Main Form Buttons --------------
            case "Create New Invoice":
                createNewInvoice();
                break;
            case "Delete Invoice":
                deleteInvoice();
                break;
            case "Add Item":
                AddItem();
                break;
            case "Delete Item":
                deleteItem();
                break;
            //------------ End Main Form Buttons ---------------
            //------------ Invoice Form Buttons ------------
            case "newInvoiceCancel":
                newInvoiceCancel();
                break;
            case "newInvoiceOk":
                newInvoiceOk();
                break;
            //------------ End Invoice form button ------------
            //------------ New Line Buttons --------------------
            case "newLineOk":
                newLineOk();
                break;
            case "newLineCancel":
                newLineCancel();
                break;
            //------------ End new Line Buttons --------------
        }
    }
    
    private void createNewInvoice() {
        if(mainForm.getInvoicesTblMdl() != null){
            invoiceForm = new InvoiceForm(mainForm);
            invoiceForm.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(mainForm, 
                    "Please Load File before creating new invoice.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteInvoice() {
        int selectedRow = mainForm.getInvoiceTable().getSelectedRow();
        if (selectedRow != -1) {
            mainForm.getInvoicesArray().remove(selectedRow);
            mainForm.getInvoicesTblMdl().fireTableDataChanged();
        }
    }

    private void AddItem() {
        if(mainForm.getInvoiceTable().getSelectedRow() > -1){
            lineForm = new LineForm(mainForm);
            lineForm.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null,
                "please choose an invoice before adding an item.",
                "Warning Message",
                JOptionPane.WARNING_MESSAGE);
        }  
    }

    private void deleteItem() {
        int selectedRow = mainForm.getLineTable().getSelectedRow();

        if (selectedRow > -1) {
            LinesTableModel linesTblMdl = (LinesTableModel) mainForm.getLineTable().getModel();
            linesTblMdl.getLinesArray().remove(selectedRow);
            linesTblMdl.fireTableDataChanged();
            mainForm.getInvoicesTblMdl().fireTableDataChanged();
        }
    }

    private void newInvoiceCancel() {
        invoiceForm.setVisible(false);
        invoiceForm.dispose();
        invoiceForm = null;
    }

    private void newInvoiceOk() {
        String date = invoiceForm.getTxtDate().getText();
        String customer = invoiceForm.getTxtCustomer().getText();
        int no = mainForm.getNewInvNo();
        try {
            String[] dateSplit = date.split("/");  
            if (dateSplit.length < 3) {
                JOptionPane.showMessageDialog(mainForm, 
                        "Date Format is incorrect. Please write date in format DD/MM/YYYY.", 
                        "Warning", JOptionPane.ERROR_MESSAGE);
            } else {
                int day = Integer.parseInt(dateSplit[0]);
                int month = Integer.parseInt(dateSplit[1]);
                int year = Integer.parseInt(dateSplit[2]);
                if (day > 31 || month > 12) {
                    JOptionPane.showMessageDialog(mainForm, 
                        "Date Format is incorrect. Please write date in format DD/MM/YYYY.", 
                        "Warning", JOptionPane.ERROR_MESSAGE);
                } else {
                    InvoiceHeader invoice = new InvoiceHeader(no, date, customer);
                    mainForm.getInvoicesArray().add(invoice);
                    mainForm.getInvoicesTblMdl().fireTableDataChanged();
                    invoiceForm.setVisible(false);
                    invoiceForm.dispose();
                    invoiceForm = null;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainForm, 
                        "Date Format is incorrect. Please write date in format DD/MM/YYYY.", 
                        "Warning", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void newLineOk() {
        String item = lineForm.getTxtItemName().getText();
        String countStr = lineForm.getTxtItemCount().getText();
        String priceStr = lineForm.getTxtItemPrice().getText();
        if(isInteger(countStr) && isDouble(priceStr)){
            int count = Integer.parseInt(lineForm.getTxtItemCount().getText());
            double price = Double.parseDouble(lineForm.getTxtItemPrice().getText());
            if(count >= 0 && price >= 0){
                int selectedRow = mainForm.getInvoiceTable().getSelectedRow();
                if (selectedRow > -1) {
                    InvoiceHeader invoice = mainForm.getInvoicesArray().get(selectedRow);
                    InvoiceLine line = new InvoiceLine(item, price, count, invoice);
                    invoice.getLines().add(line);
                    LinesTableModel linesTblMdl = (LinesTableModel) mainForm.getLineTable().getModel();
                    linesTblMdl.fireTableDataChanged();
                    mainForm.getInvoicesTblMdl().fireTableDataChanged();
                }
                lineForm.setVisible(false);
                lineForm.dispose();
                lineForm = null;
            }else if(price<0){
                JOptionPane.showMessageDialog(mainForm, 
                "Item price can't be negative. please write appropiate price.", 
                "Warning", JOptionPane.ERROR_MESSAGE);
            }
            else if(count<0){
                JOptionPane.showMessageDialog(mainForm, 
            "Item count can't be negative. please write appropiate count.", 
                "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }else if(!isInteger(countStr)){
            JOptionPane.showMessageDialog(mainForm, 
           "please write Item count in numeric Format.", 
            "Warning", JOptionPane.ERROR_MESSAGE);
        }else if(!isDouble(priceStr)){
            JOptionPane.showMessageDialog(mainForm, 
           "please write Item price in numeric Format.", 
            "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void newLineCancel() {
        lineForm.setVisible(false);
        lineForm.dispose();
        lineForm = null;
    }
    
    public static boolean isInteger(String strNum) {
        if (strNum == null) {return false;}
        try {int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {return false;}
        return true;
    }
    
    public static boolean isDouble(String strNum) {
        if (strNum == null) {return false;}
        try {double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {return false;}
        return true;
    }
}
