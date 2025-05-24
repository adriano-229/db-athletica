// src/main/java/com/adriano/dao/IngresoDAO.java
package com.adriano.dao;

import com.adriano.model.Ingreso;

public class IngresoDAO extends BaseDAO<Ingreso, Integer> {
    public IngresoDAO() {
        super(Ingreso.class);
    }
}