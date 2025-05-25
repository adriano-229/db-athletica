package com.adriano.dao;

import com.adriano.model.Asistencia;

public class AsistenciaDAO extends BaseDAO<Asistencia, Integer> {
    public AsistenciaDAO() {
        super(Asistencia.class);
    }
}