// src/main/java/com/adriano/dao/GastoDAO.java
package com.adriano.dao;

import com.adriano.model.Gasto;

public class GastoDAO extends BaseDAO<Gasto, Integer> {
    public GastoDAO() {
        super(Gasto.class);
    }
}