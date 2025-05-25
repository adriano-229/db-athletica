package com.adriano.dao;

import com.adriano.model.Contrato;

public class ContratoDAO extends BaseDAO<Contrato, Integer> {
    public ContratoDAO() {
        super(Contrato.class);
    }
}