package com.invoice.management.api.controller;

import com.invoice.management.api.exception.InvoiceNotFoundException;
import com.invoice.management.api.exception.ValidationException;
import com.invoice.management.api.service.Invoice;
import com.invoice.management.api.service.InvoiceManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceManagementController {

    @Autowired
    private InvoiceManagementService invoiceManagementService;

    @RequestMapping(value = "/details")
    public List< Invoice > getInvoiceDetails(){
        return invoiceManagementService.getInvoiceDetails();
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addInvoice(@RequestBody Invoice invoice) throws ValidationException {

        invoiceManagementService.addinvoice(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);

    }
    @GetMapping("/{id}")
    public Invoice findOne(@PathVariable int id) {
        return invoiceManagementService.findById(id);
    }
}
