package com.teletecnics.invoices_back.mapper;

import com.teletecnics.invoices_back.dto.SupplierRequestDTO;
import com.teletecnics.invoices_back.dto.SupplierResponseDTO;
import com.teletecnics.invoices_back.model.Supplier;

public class SupplierMapper {

    public static Supplier toEntity(SupplierRequestDTO dto){
        Supplier s = new Supplier();

        s.setCif(dto.getCif());
        s.setNombre(dto.getNombre());
        s.setDireccion(dto.getDireccion());
        s.setCiudad(dto.getCiudad());
        s.setCodigoPostal(dto.getCodigoPostal());
        s.setEmail(dto.getEmail());
        s.setTelfono(dto.getTelefono());

        return s;
    }
    public static SupplierResponseDTO toDTO(Supplier s){
        SupplierResponseDTO dto = new SupplierResponseDTO();

        dto.setId(s.getId());
        dto.setCif(s.getCif());
        dto.setNombre(s.getNombre());
        dto.setDireccion(s.getDireccion());
        dto.setCiudad(s.getCiudad());
        dto.setCodigoPostal(s.getCodigoPostal());
        dto.setEmail(dto.getEmail());
        dto.setTelefono(dto.getTelefono());

        return dto;
    }

    public static void updateEntity(Supplier s, SupplierResponseDTO dto){
        s.setCif(dto.getCif());
        s.setNombre(dto.getNombre());
        s.setDireccion(dto.getDireccion());
        s.setCiudad(dto.getCiudad());
        s.setCodigoPostal(dto.getCodigoPostal());
        s.setEmail(dto.getEmail());
        s.setTelfono(dto.getTelefono());
    }
}
