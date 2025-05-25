package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "profesor", schema = "people")
@PrimaryKeyJoinColumn(name = "documento")
public class Profesor extends Persona {

    @Column(name = "fecha_incorporacion", nullable = false)
    private Date fechaIncorporacion;

    public Date getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(Date fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }
}