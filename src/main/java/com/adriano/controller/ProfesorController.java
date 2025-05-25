package com.adriano.controller;

import com.adriano.dao.ProfesorDAO;
import com.adriano.model.Profesor;

public class ProfesorController extends BaseController<Profesor, Integer> {
    private final ProfesorDAO profesorDAO;

    public ProfesorController() {
        super(new ProfesorDAO());
        this.profesorDAO = (ProfesorDAO) dao;
    }
}