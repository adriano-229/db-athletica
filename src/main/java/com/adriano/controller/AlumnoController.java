package com.adriano.controller;

import com.adriano.dao.AlumnoDAO;
import com.adriano.model.Alumno;

import java.util.Date;
import java.util.List;

public class AlumnoController extends BaseController<Alumno, Integer> {
    private final AlumnoDAO alumnoDAO;

    public AlumnoController() {
        super(new AlumnoDAO());
        this.alumnoDAO = (AlumnoDAO) dao;
    }

    public List<Alumno> findExpiredCertificates(Date currentDate) {
        return alumnoDAO.findExpiredCertificates(currentDate);
    }
}