package com.teletecnics.invoices_back.mapper;

import com.teletecnics.invoices_back.dto.InvoiceRequestDTO;
import com.teletecnics.invoices_back.dto.InvoiceResponseDTO;
import com.teletecnics.invoices_back.model.Invoice;

public class InvoiceMapper {

    public static Invoice toEntity(InvoiceRequestDTO dto){
        Invoice i  = new Invoice();

        i.setNumeroFactura(dto.getNumeroFactura());
        i.setFechaFactura(dto.getFechaFactura());
        i.setClienteId(dto.getClienteId());
        i.setDescripcion(dto.getDescripcion());
        i.setBaseImponible(dto.getBaseImponible());
        i.setTipoIva(dto.getTipoIva());
        i.setFechaPrevistaCobro(dto.getFechaPrevistaCobro());
        i.setCobrada(dto.getCobrada());

        return i;
    }

    public static InvoiceResponseDTO toDTO(Invoice i){
        InvoiceResponseDTO dto = new InvoiceResponseDTO();

        dto.setId(i.getId());
        dto.setNumeroFactura(i.getNumeroFactura());
        dto.setFechaFactura(i.getFechaFactura());

        dto.setClienteId(i.getClienteId());

        if (i.getCliente() != null) {
            dto.setClienteNombre(i.getCliente().getNombre());
        }


        dto.setDescripcion(i.getDescripcion());
        dto.setBaseImponible(i.getBaseImponible());
        dto.setTipoIva(i.getTipoIva());
        dto.setImporteIva(i.getImporteIva());
        dto.setTotalFactura(i.getTotalFactura());

        dto.setFechaPrevistaCobro(i.getFechaPrevistaCobro());
        dto.setCobrada(i.getCobrada());

        return dto;

    }

    public static void updateEntity (Invoice i, InvoiceRequestDTO dto){
        i.setNumeroFactura(dto.getNumeroFactura());
        i.setFechaFactura(dto.getFechaFactura());
        i.setClienteId(dto.getClienteId());
        i.setDescripcion(dto.getDescripcion());
        i.setBaseImponible(dto.getBaseImponible());
        i.setTipoIva(dto.getTipoIva());
        i.setFechaPrevistaCobro(dto.getFechaPrevistaCobro());
        i.setCobrada(dto.getCobrada());
    }
}
