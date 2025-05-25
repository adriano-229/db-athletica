package com.adriano.controller;

import com.adriano.dao.EncargadoDAO;
import com.adriano.model.Encargado;

public class EncargadoController extends BaseController<Encargado, Integer> {
    private final EncargadoDAO encargadoDAO;

    public EncargadoController() {
        super(new EncargadoDAO());
        this.encargadoDAO = (EncargadoDAO) dao;
    }
}