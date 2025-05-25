package com.adriano.dao;

import com.adriano.model.Alumno;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

public class AlumnoDAO extends BaseDAO<Alumno, Integer> {
    public AlumnoDAO() {
        super(Alumno.class);
    }

    public List<Alumno> findExpiredCertificates(Date currentDate) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT a FROM Alumno a WHERE a.fechaVenceCertificado < :currentDate";
            return em.createQuery(jpql, Alumno.class)
                    .setParameter("currentDate", currentDate)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}