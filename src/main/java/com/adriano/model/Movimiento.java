package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "movimiento", schema = "economy")
public class Movimiento {
    @Id
    private Integer id;

    @Column(nullable = false)
    private float monto;

    @Column(nullable = false)
    private Date fecha;

    // getters and setters
}