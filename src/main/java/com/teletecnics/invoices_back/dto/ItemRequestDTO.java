package com.teletecnics.invoices_back.dto;

import java.math.BigDecimal;

public class ItemRequestDTO {
    private String telcode;
    private String descripcion;
    private Long proveedorId;
    private BigDecimal horas;
    private Double precioCoste;
    private Double precioVenta;

    //Getters and Setters


    public String getTelcode() {
        return telcode;
    }

    public void setTelcode(String telcode) {
        this.telcode = telcode;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public BigDecimal getHoras() {
        return horas;
    }

    public void setHoras(BigDecimal horas) {
        this.horas = horas;
    }

    public Double getPrecioCoste() {
        return precioCoste;
    }

    public void setPrecioCoste(Double precioCoste) {
        this.precioCoste = precioCoste;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }
}
