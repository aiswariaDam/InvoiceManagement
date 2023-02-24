package com.invoice.management.api.service;

import com.invoice.management.api.exception.InvoiceNotFoundException;
import com.invoice.management.api.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InvoiceManagementService {
    public List<Invoice> getInvoiceDetails();

    public void addinvoice(Invoice invoice) throws ValidationException;

    public Invoice findById(int id) throws InvoiceNotFoundException;
}


