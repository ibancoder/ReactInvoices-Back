package com.teletecnics.invoices_back.model;

import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Table(name = "invoices")



public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroFactura;
    private LocalDate fechaFactura; //LocalDate mapea bien con strings de fecha
    @Column(name="cliente_id")
    private Long clienteId;

    @ManyToOne
    @JoinColumn(name= "cliente_id", insertable = false, updatable = false)
    private Client cliente;  //Para cuando el backend devuelva el objeto cliente

    private String descripcion;
    private Double baseImponible;
    private Integer tipoIva; //0 o 21
    private Double importeIva;
    private Double totalFactura;


    //Constructor
    public Invoice(){

    }
    public Boolean getCobrada() {
        return cobrada;
    }

    //Getters and Setters
    public void setCobrada(Boolean cobrada) {
        this.cobrada = cobrada;
    }

    public LocalDate getFechaPrevistaCobro() {
        return fechaPrevistaCobro;
    }

    public void setFechaPrevistaCobro(LocalDate fechaPrevistaCobro) {
        this.fechaPrevistaCobro = fechaPrevistaCobro;
    }

    public Double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public Double getImporteIva() {
        return importeIva;
    }

    public void setImporteIva(Double importeIva) {
        this.importeIva = importeIva;
    }

    public Integer getTipoIva() {
        return tipoIva;
    }

    public void setTipoIva(Integer tipoIva) {
        this.tipoIva = tipoIva;
    }

    public Double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(Double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private LocalDate fechaPrevistaCobro;
    private Boolean cobrada;
}
