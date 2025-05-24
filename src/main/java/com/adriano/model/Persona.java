package com.adriano.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "persona", schema = "people")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    private int documento;
    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    public Persona(int doc, String nombre, LocalDate fecha, char sexo) {
        this.documento = doc;
        this.nombreCompleto = nombre;
        this.fechaNacimiento = fecha;
        this.sexo = Sexo.valueOf(String.valueOf(sexo));
    }

    public Persona() {

    }

    // Getters and Setters
    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
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

    public enum Sexo {M, F}
}
