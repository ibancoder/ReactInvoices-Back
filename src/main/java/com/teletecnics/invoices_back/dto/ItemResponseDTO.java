package com.teletecnics.invoices_back.dto;

import java.math.BigDecimal;

public class ItemResponseDTO {

    private Long id;
    private String telcode;
    private String descripcion;
    private Long proveedorId;
    private String proveedorNombre;
    private BigDecimal horas;
    private Double precioCoste;
    private Double precioVenta;

    //Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getProveedorNombre(){
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre){
        this.proveedorNombre = proveedorNombre;
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
