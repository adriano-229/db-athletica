package com.adriano.controller;

import com.adriano.dao.PersonaDAO;
import com.adriano.model.Persona;

public class PersonaController extends BaseController<Persona, Integer> {
    public PersonaController() {
        super(new PersonaDAO());
    }

    public void deletePersonaDoc(int doc) {
        Persona persona = dao.get(doc);
        if (persona != null) {
            dao.delete(persona);
        } else {
            System.out.println("Persona not found with doc: " + doc);
        }
    }
}