package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "alumno", schema = "people")
@PrimaryKeyJoinColumn(name = "documento")
public class Alumno extends Persona {
    @Column(name = "fecha_vence_certificado", nullable = false)
    private Date fechaVenceCertificado;

    public Date getFechaVenceCertificado() {
        return fechaVenceCertificado;
    }

    public void setFechaVenceCertificado(Date fechaVenceCertificado) {
        this.fechaVenceCertificado = fechaVenceCertificado;
    }
}