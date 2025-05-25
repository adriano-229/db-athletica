// src/main/java/com/adriano/dao/EgresoContratoDAO.java
package com.adriano.dao;

import com.adriano.model.EgresoContrato;

public class EgresoContratoDAO extends BaseDAO<EgresoContrato, Integer> {
    public EgresoContratoDAO() {
        super(EgresoContrato.class);
    }
}