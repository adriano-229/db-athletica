package com.adriano.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gasto", schema = "economy")
public class Gasto extends Egreso {
    @Id
    private Integer id;

    private String descripcion;

    // getters and setters
}