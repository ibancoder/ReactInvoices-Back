package com.teletecnics.invoices_back.repository;

import com.teletecnics.invoices_back.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long> {
}
