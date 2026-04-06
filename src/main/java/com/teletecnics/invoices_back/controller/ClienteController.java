package com.teletecnics.invoices_back.controller;

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

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client){
        Client savedClient = clientRepository.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client clientData) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setCif(clientData.getCif());
                    client.setNombre(clientData.getNombre());
                    client.setDireccion(clientData.getDireccion());
                    client.setCiudad(clientData.getCiudad());
                    client.setCodigoPostal(clientData.getCodigoPostal());
                    client.setEmail(clientData.getEmail());
                    client.setTelefono(clientData.getTelefono());
                    return ResponseEntity.ok(clientRepository.save(client));
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
