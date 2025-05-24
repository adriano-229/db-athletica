package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "profesor", schema = "people")
@PrimaryKeyJoinColumn(name = "documento")
public class Profesor extends Persona {

    @Column(name = "fecha_incorporacion", nullable = false)
    private LocalDate fechaIncorporacion;

    public Profesor(int doc, String nombre, LocalDate fecha, char sexo, LocalDate fechaIncorporacion) {
        super(doc, nombre, fecha, sexo);
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public Profesor() {

    }

    public LocalDate getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(LocalDate fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }
}
