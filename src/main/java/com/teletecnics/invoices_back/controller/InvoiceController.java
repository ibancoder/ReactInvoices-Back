package com.teletecnics.invoices_back.controller;

import com.teletecnics.invoices_back.dto.InvoiceResponseDTO;
import com.teletecnics.invoices_back.model.Invoice;
import com.teletecnics.invoices_back.repository.ClientRepository;
import com.teletecnics.invoices_back.repository.InvoiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*") //En producción, cambiar "*" por la URL del frontend
public class InvoiceController {
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    public InvoiceController(InvoiceRepository invoiceRepository, ClientRepository clientRepository){
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<InvoiceResponseDTO> getAll(){
        return invoiceRepository.findAll().stream().map(this::toDTO).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id){
        return invoiceRepository.findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice){
        calculateInvoiceTotals(invoice);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable Long id, @RequestBody Invoice invoiceData) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    invoice.setNumeroFactura(invoiceData.getNumeroFactura());
                    invoice.setFechaFactura(invoiceData.getFechaFactura());
                    invoice.setClienteId(invoiceData.getClienteId());
                    invoice.setDescripcion(invoiceData.getDescripcion());
                    invoice.setBaseImponible(invoiceData.getBaseImponible());
                    invoice.setTipoIva(invoiceData.getTipoIva());
                    invoice.setFechaPrevistaCobro(invoiceData.getFechaPrevistaCobro());
                    invoice.setCobrada(invoiceData.getCobrada());

                    calculateInvoiceTotals(invoice);
                    return ResponseEntity.ok(invoiceRepository.save(invoice));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (invoiceRepository.existsById(id)) {
            invoiceRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    //El servidor siempre valída los cálculos financieros
    private void calculateInvoiceTotals(Invoice invoice){
        if (invoice.getBaseImponible() != null && invoice.getTipoIva() != null){
            double importeIva = invoice.getBaseImponible() * (invoice.getTipoIva() /100.0);
            invoice.setImporteIva(importeIva);
            invoice.setTotalFactura(invoice.getBaseImponible() +importeIva);
        }
    }

}
