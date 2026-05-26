package com.teletecnics.invoices_back.model;

import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name="articulos")


public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String telcode;

    private String descripcion;

    @Column(name = "proveedor_id")
    private Long proveedorId;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", insertable = false, updatable = false)
    private Supplier proveedor; // Para cuando el backend devuelva el objeto Supplier

    private BigDecimal horas;
    private Double precioCoste;
    private Double precioVenta;

    //Constructor

    public Item() {
    }

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

    public Supplier getProveedor() {
        return proveedor;
    }

    public void setProveedor(Supplier proveedor) {
        this.proveedor = proveedor;
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