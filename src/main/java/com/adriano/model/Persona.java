package com.adriano.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "persona", schema = "people")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Persona {
    @Id
    @Column(name = "documento")
    private Integer documento;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "sexo")
    private Sexo sexo;

    // Getters and setters
    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public enum Sexo {M, F, X}
}