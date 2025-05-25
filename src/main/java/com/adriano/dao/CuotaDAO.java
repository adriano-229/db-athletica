// src/main/java/com/adriano/dao/CuotaDAO.java
package com.adriano.dao;

import com.adriano.model.Cuota;

public class CuotaDAO extends BaseDAO<Cuota, Integer> {
    public CuotaDAO() {
        super(Cuota.class);
    }
}