package com.adriano.dao;

import com.adriano.model.Movimiento;

public class MovimientoDAO extends BaseDAO<Movimiento, Integer> {
    public MovimientoDAO() {
        super(Movimiento.class);
    }
}