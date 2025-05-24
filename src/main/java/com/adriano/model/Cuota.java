package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuota", schema = "economy")
public class Cuota extends Ingreso {
    @Id
    private Integer id;

    @Column(name = "estimulos_totales", nullable = false)
    private Integer estimulosTotales;

    // getters and setters
}