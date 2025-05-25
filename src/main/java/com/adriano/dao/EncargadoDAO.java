// src/main/java/com/adriano/dao/EncargadoDAO.java
package com.adriano.dao;

import com.adriano.model.Encargado;

public class EncargadoDAO extends BaseDAO<Encargado, Integer> {
    public EncargadoDAO() {
        super(Encargado.class);
    }
}