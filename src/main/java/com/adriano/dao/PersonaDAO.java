package com.adriano.dao;

import com.adriano.model.Persona;

public class PersonaDAO extends BaseDAO<Persona, Integer> {
    public PersonaDAO() {
        super(Persona.class);
    }
}