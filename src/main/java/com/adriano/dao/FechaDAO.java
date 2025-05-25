// src/main/java/com/adriano/dao/FechaDAO.java
package com.adriano.dao;

import com.adriano.model.Fecha;

import java.util.Date;

public class FechaDAO extends BaseDAO<Fecha, Date> {
    public FechaDAO() {
        super(Fecha.class);
    }
}