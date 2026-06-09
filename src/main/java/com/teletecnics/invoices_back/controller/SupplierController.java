package com.teletecnics.invoices_back.controller;

import com.teletecnics.invoices_back.dto.SupplierRequestDTO;
import com.teletecnics.invoices_back.dto.SupplierResponseDTO;
import com.teletecnics.invoices_back.mapper.SupplierMapper;
import com.teletecnics.invoices_back.model.Supplier;
import com.teletecnics.invoices_back.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*") // En producción cambiar "*" por la URL del frontend
public class SupplierController {

    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public List<SupplierResponseDTO> getAll() { // Unificado a DTO
        return supplierRepository.findAll().stream()
                .map(SupplierMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getById(@PathVariable Long id) { // Unificado a DTO
        return supplierRepository.findById(id)
                .map(supplier -> ResponseEntity.ok(SupplierMapper.toDTO(supplier)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/nombre")
    public List<SupplierResponseDTO> searchByName(@RequestParam String nombre) {
        return supplierRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(SupplierMapper::toDTO)
                .toList();
    }

    @GetMapping("/search/cif")
    public List<SupplierResponseDTO> searchByCif(@RequestParam String cif) {
        return supplierRepository.findByCifContainingIgnoreCase(cif).stream()
                .map(SupplierMapper::toDTO)
                .toList();
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> create(@RequestBody SupplierRequestDTO dto) {
        Supplier supplier = SupplierMapper.toEntity(dto);
        Supplier saved = supplierRepository.save(supplier);
        return new ResponseEntity<>(SupplierMapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(@PathVariable Long id, @RequestBody SupplierRequestDTO dto) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    SupplierMapper.updateEntity(supplier, dto);
                    return ResponseEntity.ok(SupplierMapper.toDTO(supplierRepository.save(supplier)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build(); // Añadido para manejar si el ID no existe
    }
}