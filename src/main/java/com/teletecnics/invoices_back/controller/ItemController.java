package com.teletecnics.invoices_back.controller;


import com.teletecnics.invoices_back.dto.ItemRequestDTO;
import com.teletecnics.invoices_back.dto.ItemResponseDTO;
import com.teletecnics.invoices_back.mapper.ItemMapper;
import com.teletecnics.invoices_back.model.Item;
import com.teletecnics.invoices_back.repository.ItemRepository;
import com.teletecnics.invoices_back.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins="*") //En producción cambiar "*" por la URL del frontend

public class ItemController {
    private final ItemRepository itemRepository;
    private final SupplierRepository supplierRepository;
    public ItemController(ItemRepository itemRepository, SupplierRepository supplierRepository){
        this.itemRepository = itemRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id){
        return itemRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/nombre")
    public List<ItemResponseDTO> searchBySupplierName(@RequestParam String nombre){
        return itemRepository.findByProveedor_nombreContainingIgnoreCase(nombre).stream().map(ItemMapper::toDTO).toList();
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> create (@RequestBody ItemRequestDTO dto){
        Item item = ItemMapper.toEntity(dto);
        Item saved = itemRepository.save(item);
        return new ResponseEntity<>(ItemMapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> update (@PathVariable Long id, @RequestBody ItemRequestDTO dto) {
        return itemRepository.findById(id).map(item -> {
            ItemMapper.updateEntity(item, dto);

            return ResponseEntity.ok(ItemMapper.toDTO(item));
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
