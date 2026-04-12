package com.teletecnics.invoices_back.dto;

import java.time.LocalDate;

public class InvoiceResponseDTO {

    private Long id;
    private String numeroFactura;
    private LocalDate fechaFactura;

    private Long clienteId;
    private String clienteNombre;

    private String descripcion;
    private Double baseImponible;
    private Integer tipoIva;
    private Double importeIva;
    private Double totalFactura;

    private LocalDate fechaPrevistaCobro;
    private Boolean cobrada;

    //Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(Double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public Integer getTipoIva() {
        return tipoIva;
    }

    public void setTipoIva(Integer tipoIva) {
        this.tipoIva = tipoIva;
    }

    public Double getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(Double importeIva) {
        this.importeIva = importeIva;
    }

    public Double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public LocalDate getFechaPrevistaCobro() {
        return fechaPrevistaCobro;
    }

    public void setFechaPrevistaCobro(LocalDate fechaPrevistaCobro) {
        this.fechaPrevistaCobro = fechaPrevistaCobro;
    }

    public Boolean getCobrada() {
        return cobrada;
    }

    public void setCobrada(Boolean cobrada) {
        this.cobrada = cobrada;
    }
}
