package com.invoice.management.api.service;

import com.invoice.management.api.exception.InvoiceNotFoundException;
import com.invoice.management.api.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InvoiceManagementServiceImpl implements InvoiceManagementService{

    private static List<Invoice> invoiceList = new ArrayList<>();
    static {
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
        invoice1.setSupplier("ABC");
        invoice1.setInvoiceAmount("1000");

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(2);
        invoice2.setSupplier("XYZ");
        invoice2.setInvoiceAmount("1500");

        invoiceList.add(invoice1);
        invoiceList.add(invoice2);
    }

    public List<Invoice> getInvoiceDetails(){
        return invoiceList;
    }

    @Override
    public void addinvoice(Invoice invoice) throws ValidationException {
        if(invoice.getSupplier() == null){
            throw new ValidationException("Supplier name can not be empty");
        }
        if(!isValidName(invoice.getSupplier())){
            throw new ValidationException("Supplier name should have only characters");
        }
        if(!isValidAmount(invoice.getInvoiceAmount())){
            throw new ValidationException("Not a valid amount");
        }
        invoiceList.add(invoice);
    }

    @Override
    public Invoice findById(int id) {
        System.out.println("invoiceList.size() "+invoiceList.size());
        if(id < invoiceList.size()){
            return invoiceList.get(id);
        }else{
            throw new InvoiceNotFoundException("Invoice not found with id "+ id);
        }
    }

    // Function to validate the username
    public static boolean isValidName(String name)
    {
        // Regex to check valid username.
        String regex = "[A-Z][a-z]*";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);

        System.out.println("Validation check "+ m.matches());
        return m.matches();
    }

    // Function to validate the username
    public static boolean isValidAmount(String amount)
    {
        // Regex to check valid username.
        String regex = "[0-9]*";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(amount);

        System.out.println("Validation check "+ m.matches());
        return m.matches();
    }
}
