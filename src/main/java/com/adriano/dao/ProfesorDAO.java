package com.adriano.dao;

import com.adriano.model.Profesor;

public class ProfesorDAO extends BaseDAO<Profesor, Integer> {
    public ProfesorDAO() {
        super(Profesor.class);
    }
}