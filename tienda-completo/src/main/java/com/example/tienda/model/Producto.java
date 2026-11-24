package com.example.tienda.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad JPA: cada objeto Producto es una fila de la tabla "producto".
 * Este es el mapeo objeto-relacional (Hibernate lo implementa).
 */
@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    private Integer stock;
}
