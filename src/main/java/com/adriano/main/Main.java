package com.adriano.main;

import com.adriano.model.Persona;
import com.adriano.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.adriano.view.PersonaView;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        new PersonaView();
        Persona per = new Persona();
        per.setDocumento(4567);
        per.setNombreCompleto("Ana Díaz");
        per.setFechaNacimiento(LocalDate.of(1990, 5, 23));
        per.setSexo(Persona.Sexo.F);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(per);
            tx.commit();
        }

    }
}
