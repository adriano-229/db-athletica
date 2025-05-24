package com.adriano.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "ingreso", schema = "economy")
public class Ingreso extends Movimiento {
    @Id
    private Integer id;

    // getters and setters
}