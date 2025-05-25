package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "egresocontrato", schema = "economy")
public class EgresoContrato {
    @Id
    @Column(name = "id_egreso")
    private Integer idEgreso;

    @Column(name = "id_contrato", unique = true, nullable = false)
    private Integer idContrato;

    // getters and setters
}