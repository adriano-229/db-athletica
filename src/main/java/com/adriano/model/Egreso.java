package com.adriano.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "egreso", schema = "economy")
public class Egreso extends Movimiento {
    @Id
    private Integer id;

    // getters and setters
}