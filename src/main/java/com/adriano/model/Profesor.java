package com.adriano.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor", schema = "people")
@PrimaryKeyJoinColumn(name = "documento")
public class Profesor extends Persona {

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}