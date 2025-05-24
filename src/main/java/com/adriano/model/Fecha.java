package com.adriano.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "fecha", schema = "economy")
public class Fecha {
    @Id
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;

    // getters and setters
}