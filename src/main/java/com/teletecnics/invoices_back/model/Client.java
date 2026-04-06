package com.teletecnics.invoices_back.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clientes")
@Data //Genera getters, setters, toString, equals y hashCode con Lombok
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cif;

    private String nombre;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    @Column(nullable = false, unique = true)
    private String email;
    private String telefono;


}
