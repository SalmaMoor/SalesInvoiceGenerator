/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sales.controller;

import com.sales.model.InvoiceHeader;
import com.sales.model.InvoiceLine;
import com.sales.model.InvoicesTableModel;
import com.sales.view.InvoiceForm;
import com.sales.view.LineForm;
import com.sales.view.MainForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author SURE
 */
public class MenuActions implements ActionListener{
    private MainForm mainForm;
    

    public MenuActions(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {
            case "Load File":
                loadFile();
                break;
            case "Save File":
                saveFile();
                break;
        }
    }
    
    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        try {
            int result = fc.showOpenDialog(mainForm);
            if (result == JFileChooser.APPROVE_OPTION) {
                File invoicesFile = fc.getSelectedFile();
                Path invoicesPath = Paths.get(invoicesFile.getAbsolutePath());
                List<String> invoicesList = Files.readAllLines(invoicesPath);
                
                ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
                for (String invoiceInCsv : invoicesList) {
                    try {
                        String[] invoiceVariables = invoiceInCsv.split(",");
                        int invNo = Integer.parseInt(invoiceVariables[0]);
                        String invDate = invoiceVariables[1];
                        String customer = invoiceVariables[2];

                        InvoiceHeader invoice = new InvoiceHeader(invNo, invDate, customer);
                        invoicesArray.add(invoice);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainForm, 
                                "Invalid Invoice format", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                result = fc.showOpenDialog(mainForm);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineList = Files.readAllLines(linePath);
                    
                    for (String lineInCsv : lineList) {
                        try {
                            String[] lineVariable = lineInCsv.split(",");
                            int invNo = Integer.parseInt(lineVariable[0]);
                            String itemName = lineVariable[1];
                            double itemPrice = Double.parseDouble(lineVariable[2]);
                            int count = Integer.parseInt(lineVariable[3]);
                            InvoiceHeader inv = null;
                            for (InvoiceHeader invoice : invoicesArray) {
                                if (invoice.getInvoiceNumber() == invNo) {
                                    inv = invoice;
                                    break;
                                }
                            }
                            InvoiceLine invoiceLine = new InvoiceLine(itemName, itemPrice, count, inv);
                            inv.getLines().add(invoiceLine);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(mainForm, 
                                    "Invalid Line Format", 
                                    "Error", 
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                mainForm.setInvoicesArray(invoicesArray);
                InvoicesTableModel invoicesTblMdl = new InvoicesTableModel(invoicesArray);
                mainForm.setInvoicesTblMdl(invoicesTblMdl);
                mainForm.getInvoiceTable().setModel(invoicesTblMdl);
                mainForm.getInvoicesTblMdl().fireTableDataChanged();
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(mainForm, 
                    "File Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainForm, 
                    "Wrong File Format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void saveFile() {
        ArrayList<InvoiceHeader> invoicesArray = mainForm.getInvoicesArray();
        String invoices = "";
        String lines = "";
        for (InvoiceHeader invoice : invoicesArray) {
            String invInCsv = invoice.getInCsv();
            invoices += invInCsv + "\n";

            for (InvoiceLine line : invoice.getLines()) {
                String lineInCsv = line.getInCsv();
                lines += lineInCsv + "\n";
            }
        }
        
        try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(mainForm);
            if (result == JFileChooser.APPROVE_OPTION) {
                //--------------- Save Invoices-------------------
                File invoicesFile = fc.getSelectedFile();
                FileWriter invoicesFileWriter = new FileWriter(invoicesFile);
                invoicesFileWriter.write(invoices);
                invoicesFileWriter.flush();
                invoicesFileWriter.close();
                //-------------- Save Lines ----------------------
                result = fc.showSaveDialog(mainForm);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fc.getSelectedFile();
                    FileWriter linesFileWriter = new FileWriter(linesFile);
                    linesFileWriter.write(lines);
                    linesFileWriter.flush();
                    linesFileWriter.close();
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(mainForm, 
                    "File Not Found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(mainForm, 
                    "Wrong File Format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
