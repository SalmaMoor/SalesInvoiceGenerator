
package com.sales.model;

public class InvoiceLine {
    private String itemName;
    private double price;
    private int count;
    private InvoiceHeader invoice;

    public InvoiceLine(String itemName, double price, int count, InvoiceHeader invoice) {
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
    }

    public double getLineTotal() {
        return price * count;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Line{" + "Invoice Number = " + invoice.getInvoiceNumber() + 
                ", item = " + itemName + ", price = " + price + ", count = " + count + '}';
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }
    
    public String getInCsv() {
        return invoice.getInvoiceNumber() + "," + itemName + "," + price + "," + count;
    }
    
}
