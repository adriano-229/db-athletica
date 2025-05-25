package com.adriano.dao;

import com.adriano.model.Egreso;

public class EgresoDAO extends BaseDAO<Egreso, Integer> {
    public EgresoDAO() {
        super(Egreso.class);
    }
}