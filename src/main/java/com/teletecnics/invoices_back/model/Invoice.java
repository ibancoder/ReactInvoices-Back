package com.teletecnics.invoices_back.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "invoices")
@AllArgsConstructor
@NoArgsConstructor
@Data


public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroFactura;
    private LocalDate fechaFactura; //LocalDate mapea bien con strings de fecha
    private Long clienteId;

    @ManyToOne
    @JoinColumn(name= "cliente_id", insertable = false, updatable = false)
    private Client cliente;  //Para cuando el backend devuelva el objeto cliente

    private String descripcion;
    private Double baseImponible;
    private Integer tipoIva; //0 o 21
    private Double importeIva;
    private Double totalFactura;
    private LocalDate fechaPrevistaCobro;
    private Boolean cobrada;
}
