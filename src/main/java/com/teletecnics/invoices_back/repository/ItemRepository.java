package com.teletecnics.invoices_back.repository;

import com.teletecnics.invoices_back.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository <Item, Long> {

    //Buscar por descripcion de item
    List<Item> findByDescripcionContainingIgnoreCase (String descripcion);
}
