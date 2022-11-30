
package com.sales.model;

import java.util.ArrayList;

public class InvoiceHeader {
    private int invoiceNumber;
    private String invoiceDate;
    private String customer;
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader(int number, String date, String customer) {
        this.invoiceNumber = number;
        this.invoiceDate = date;
        this.customer = customer;
    }

    public double getInvoiceTotal() {
        double total = 0.0;
        for (InvoiceLine line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }
    
    public ArrayList<InvoiceLine> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "Invoice{" + "num=" + invoiceNumber + ", date=" + invoiceDate + ", customer=" + customer + '}';
    }
    
    public String getInCsv() {
        return invoiceNumber + "," + invoiceDate + "," + customer;
    }
    
}
