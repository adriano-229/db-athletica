package com.adriano.controller;

import java.util.List;

public class BaseController<T, ID> {
    protected final com.adriano.dao.BaseDAO<T, ID> dao;

    public BaseController(com.adriano.dao.BaseDAO<T, ID> dao) {
        this.dao = dao;
    }

    public void save(T entity) {
        dao.save(entity);
    }

    public T get(ID id) {
        return dao.get(id);
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public void update(T entity) {
        dao.update(entity);
    }

    public void delete(T entity) {
        dao.delete(entity);
    }
}