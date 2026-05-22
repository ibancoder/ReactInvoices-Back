package com.teletecnics.invoices_back.repository;

import com.teletecnics.invoices_back.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository <Client, Long> {

    List<Client> findByNombreContainingIgnoreCase(String nombre);
    List<Client> findByCifContainingIgnoreCase(String cif);
}
