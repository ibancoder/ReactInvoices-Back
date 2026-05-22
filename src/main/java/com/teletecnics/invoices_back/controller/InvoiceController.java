package com.teletecnics.invoices_back.controller;

import com.teletecnics.invoices_back.dto.InvoiceResponseDTO;
import com.teletecnics.invoices_back.dto.InvoiceRequestDTO;
import com.teletecnics.invoices_back.mapper.InvoiceMapper;
import com.teletecnics.invoices_back.model.Invoice;
import com.teletecnics.invoices_back.repository.ClientRepository;
import com.teletecnics.invoices_back.repository.InvoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getById(@PathVariable Long id){
        return invoiceRepository.findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/nombre")
    public List<InvoiceResponseDTO> searchByClientName(@RequestParam String nombre){
        return invoiceRepository.findByCliente_NombreContainingIgnoreCase(nombre).stream().map(InvoiceMapper::toDTO).toList();
    }

    //buscador completo
    @GetMapping("/search")
    public Page<InvoiceResponseDTO> searchInvoices(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta,
            @RequestParam(required = false) Boolean cobrada,
            Pageable pageable
    ){
        LocalDate desde = (fechaDesde != null) ? LocalDate.parse(fechaDesde) : null;
        LocalDate hasta = (fechaHasta != null) ? LocalDate.parse(fechaHasta) : null;

        return invoiceRepository.searchInvoices(nombre, desde, hasta, cobrada, pageable).map(InvoiceMapper::toDTO);
    }

    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> create(@RequestBody InvoiceRequestDTO dto){
        Invoice invoice = InvoiceMapper.toEntity(dto);
        calculateInvoiceTotals(invoice);
        Invoice saved = invoiceRepository.save(invoice);
        return new ResponseEntity<>(InvoiceMapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> update(@PathVariable Long id, @RequestBody InvoiceRequestDTO dto) {
        return invoiceRepository.findById(id)
                .map(invoice -> {
                    InvoiceMapper.updateEntity(invoice, dto);

                    calculateInvoiceTotals(invoice);
                    return ResponseEntity.ok(InvoiceMapper.toDTO(invoice));
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
