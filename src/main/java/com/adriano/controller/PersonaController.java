package com.adriano.controller;

import com.adriano.dao.PersonaDAO;
import com.adriano.model.Persona;

import java.util.List;

public class PersonaController {

    private final PersonaDAO personaDAO;

    public PersonaController() {
        this.personaDAO = new PersonaDAO();
    }

    public void addPersona(Persona persona) {
        personaDAO.save(persona);
    }

    public List<Persona> getAllPersonas() {
        return personaDAO.getAll();
    }

    public void deletePersona(Persona persona) {
        personaDAO.delete(persona);
    }

    public Persona getPersona(int documento) {
        return personaDAO.get(documento);
    }

    public void updatePersona(Persona persona) {
        personaDAO.update(persona);
    }
}
