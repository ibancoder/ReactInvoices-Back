package com.teletecnics.invoices_back.mapper;

import com.teletecnics.invoices_back.dto.ClientRequestDTO;
import com.teletecnics.invoices_back.dto.ClientResponseDTO;
import com.teletecnics.invoices_back.model.Client;

public class ClientMapper {
    public static Client toEntity(ClientRequestDTO dto){
        Client c = new Client();

        c.setCif(dto.getCif());
        c.setNombre(dto.getNombre());
        c.setDireccion(dto.getDireccion());
        c.setCiudad(dto.getCiudad());
        c.setCodigoPostal(dto.getCodigoPostal());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());

        return c;
    }
    public static ClientResponseDTO toDTO(Client c){
        ClientResponseDTO dto = new ClientResponseDTO();

        dto.setId(c.getId());
        dto.setCif(c.getCif());
        dto.setNombre(c.getNombre());
        dto.setDireccion(c.getDireccion());
        dto.setCiudad(c.getCiudad());
        dto.setCodigoPostal(c.getCodigoPostal());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());

        return dto;
    }

    public static void updateEntity(Client c, ClientRequestDTO dto){
        c.setCif(dto.getCif());
        c.setNombre(dto.getNombre());
        c.setDireccion(dto.getDireccion());
        c.setCiudad(dto.getCiudad());
        c.setCodigoPostal(dto.getCodigoPostal());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
    }

}
