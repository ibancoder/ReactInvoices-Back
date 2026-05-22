package com.teletecnics.invoices_back.controller;

import com.teletecnics.invoices_back.dto.ClientRequestDTO;
import com.teletecnics.invoices_back.dto.ClientResponseDTO;
import com.teletecnics.invoices_back.mapper.ClientMapper;
import com.teletecnics.invoices_back.model.Client;
import com.teletecnics.invoices_back.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // En produccion, cambiar "*" por la URL del frontend
public class ClienteController {
    private final ClientRepository clientRepository;

    public ClienteController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id){
        return clientRepository.findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/nombre")
    public List<ClientResponseDTO> searchByName(@RequestParam String nombre){

        return clientRepository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(ClientMapper::toDTO)
                .toList();
    }

    @GetMapping("/search/cif")
    public List<ClientResponseDTO> searchByCif(@RequestParam String cif){
        return clientRepository.findByCifContainingIgnoreCase(cif).stream().map(ClientMapper::toDTO).toList();
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody ClientRequestDTO dto){
        Client client = ClientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);

        return new ResponseEntity<>(ClientMapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @RequestBody ClientRequestDTO dto) {
        return clientRepository.findById(id)
                .map(client -> {
                    ClientMapper.updateEntity(client, dto);
                    return ResponseEntity.ok(ClientMapper.toDTO(clientRepository.save(client))
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
