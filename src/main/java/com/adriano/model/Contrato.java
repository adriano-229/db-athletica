package com.adriano.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "contrato", schema = "economy")
public class Contrato {
    @Id
    private Integer id;

    @Column(name = "fecha_emision", nullable = false)
    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "doc_profesor", referencedColumnName = "documento", nullable = false)
    private Profesor profesor;

    @Column(name = "id_egreso", nullable = false)
    private Integer idEgreso;

    // getters and setters
}