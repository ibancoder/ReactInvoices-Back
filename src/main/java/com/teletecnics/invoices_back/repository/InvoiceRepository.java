package com.teletecnics.invoices_back.repository;

import com.teletecnics.invoices_back.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice, Long> {

    //Buscar por nombre de cliente
    List<Invoice> findByCliente_NombreContainingIgnoreCase (String nombre);

    //Buscador completo (cliente + fechas + estado) mediante consulta SQL

    @Query("""
            SELECT i FROM Invoice i 
            WHERE (:nombre IS NULL OR LOWER(i.cliente.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
            AND (:fechaDesde IS NULL OR i.fechaFactura >= :fechaDesde)
            AND (:fechaHasta IS NULL OR i.fechaFactura <= :fechaHasta)
            AND (:cobrada IS NULL OR i.cobrada = :cobrada)
            """)
    Page<Invoice> searchInvoices(
            @Param("nombre") String nombre,
            @Param("fechaDesde") LocalDate fechaDesde,
            @Param("fechaHasta") LocalDate fechaHasta,
            @Param("cobrada") Boolean cobrada,
            Pageable pageable

    );
}
