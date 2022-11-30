/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InvoiceForm extends JDialog {
    private JLabel lblCustomer;
    private JLabel lblDate;
    
    private JTextField txtCustomer;
    private JTextField txtDate;
    
    private JButton btnOk;
    private JButton btnCancel;

    public InvoiceForm(MainForm mainForm) {
        setLocation(500, 400);
        lblCustomer = new JLabel("Customer Name:");
        txtCustomer = new JTextField(30);
        lblDate = new JLabel("Invoice Date:");
        txtDate = new JTextField(30);
        btnOk = new JButton("Ok");
        btnCancel = new JButton("Cancel");
        
        btnOk.setActionCommand("newInvoiceOk");
        btnCancel.setActionCommand("newInvoiceCancel");
        
        btnOk.addActionListener(mainForm.getBtnAction());
        btnCancel.addActionListener(mainForm.getBtnAction());
        setLayout(new GridLayout(3, 2));
        
        add(lblDate);
        add(txtDate);
        add(lblCustomer);
        add(txtCustomer);
        add(btnOk);
        add(btnCancel);
        
        pack();
        
    }

    public JTextField getTxtCustomer() {
        return txtCustomer;
    }

    public JTextField getTxtDate() {
        return txtDate;
    }
    
}
