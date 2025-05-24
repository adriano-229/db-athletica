package com.adriano.dao;

import com.adriano.model.Persona;
import com.adriano.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

// DAO (Data Access Object): design pattern that provides an abstract interface to some type of database or other persistence mechanism.
public class PersonaDAO {

    public void save(Persona persona) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(persona);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Persona get(int documento) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Persona.class, documento);
        }
    }

    public List<Persona> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Persona", Persona.class).list();
        }
    }

    public void update(Persona persona) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(persona);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(Persona persona) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(persona);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }
}
