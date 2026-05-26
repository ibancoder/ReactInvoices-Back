package com.teletecnics.invoices_back.mapper;

import com.teletecnics.invoices_back.dto.ItemRequestDTO;
import com.teletecnics.invoices_back.dto.ItemResponseDTO;
import com.teletecnics.invoices_back.model.Item;

public class ItemMapper {

    public static Item toEntity(ItemRequestDTO dto){

        Item i = new Item();

        i.setTelcode(dto.getTelcode());
        i.setDescripcion(dto.getDescripcion());
        i.setProveedorId(dto.getProveedorId());
        i.setHoras(dto.getHoras());
        i.setPrecioCoste(dto.getPrecioCoste());
        i.setPrecioVenta(dto.getPrecioVenta());

        return i;
    }

    public static ItemResponseDTO toDTO(Item i){
        ItemResponseDTO dto = new ItemResponseDTO();

        dto.setId(i.getId());
        dto.setDescripcion(i.getDescripcion());
        dto.setProveedorId(i.getProveedorId());

        if (i.getProveedor() != null){
            dto.setProveedorNombre(i.getProveedor().getNombre());
        }

        dto.setHoras(i.getHoras());
        dto.setPrecioCoste(i.getPrecioCoste());
        dto.setPrecioVenta(i.getPrecioVenta());

        return dto;
    }

    public static void updateEntity (Item i, ItemRequestDTO dto){
        i.setTelcode(dto.getTelcode());
        i.setDescripcion(dto.getDescripcion());
        i.setProveedorId(dto.getProveedorId());
        i.setHoras(dto.getHoras());
        i.setPrecioCoste(dto.getPrecioCoste());
        i.setPrecioVenta(dto.getPrecioVenta());
    }
}
