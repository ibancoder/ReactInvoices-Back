package com.teletecnics.invoices_back.repository;

import com.teletecnics.invoices_back.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository <Supplier, Long> {
    List<Supplier> findByNombreContainingIgnoreCase(String nombre);
    List<Supplier> findByCifContainingIgnoreCase(String cif);
}
