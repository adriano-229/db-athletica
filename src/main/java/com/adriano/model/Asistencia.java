package com.adriano.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "asistencia", schema = "people")
public class Asistencia {
    @Id
    private Integer id;

    private Date fecha;

    private java.sql.Time hora;

    @ManyToOne
    @JoinColumn(name = "doc_alumno", referencedColumnName = "documento", nullable = false)
    private Alumno alumno;

    // getters and setters
}