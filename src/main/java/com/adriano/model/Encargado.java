package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "encargado", schema = "people")
@PrimaryKeyJoinColumn(name = "documento")
public class Encargado extends Persona {


    @Column(name = "porcentaje_accionario", nullable = false)
    private Double porcentajeAccionario;

    public Double getPorcentajeAccionario() {
        return porcentajeAccionario;
    }

    public void setPorcentajeAccionario(Double porcentajeAccionario) {
        this.porcentajeAccionario = porcentajeAccionario;
    }
}