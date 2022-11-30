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


public class LineForm extends JDialog{
    
    private JLabel lblItemName;
    private JLabel lblItemPrice;
    private JLabel lblItemCount;
    
    private JTextField txtItemName;
    private JTextField txtItemPrice;
    private JTextField txtItemCount;
    
    private JButton btnOk;
    private JButton btnCancel;
    
    public LineForm(MainForm mainForm) {
        setLocation(500, 400);
        lblItemName = new JLabel("Item Name:");
        txtItemName = new JTextField(30);
        
        lblItemPrice = new JLabel("Item Price:");
        txtItemPrice = new JTextField(30);
        
        lblItemCount = new JLabel("Item Count:");
        txtItemCount = new JTextField(30);
        
        btnOk = new JButton("Ok");
        btnCancel = new JButton("Cancel");
        
        btnOk.setActionCommand("newLineOk");
        btnCancel.setActionCommand("newLineCancel");
        
        btnOk.addActionListener(mainForm.getBtnAction());
        btnCancel.addActionListener(mainForm.getBtnAction());
        setLayout(new GridLayout(4, 2));
        
        add(lblItemName);
        add(txtItemName);
        add(lblItemPrice);
        add(txtItemPrice);
        add(lblItemCount);
        add(txtItemCount);
        
        add(btnOk);
        add(btnCancel);
        
        pack();
    }

    public JTextField getTxtItemName() {
        return txtItemName;
    }

    public JTextField getTxtItemCount() {
        return txtItemCount;
    }

    public JTextField getTxtItemPrice() {
        return txtItemPrice;
    }
}
