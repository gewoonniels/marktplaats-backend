package org.example.domain.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

@Stateless
public class Dao<E extends AbstractEntity<Long>> {

    @PersistenceContext // Container managed persistence context
    protected EntityManager em;

    public Collection<E> getAll() {
        return em.createNamedQuery(typeSimple() + ".findAll", E()).getResultList();
    }

    public E getById(long id) { return em.find(E(), id); }

    public Collection<E> get(String q) {
        return null;
    }

    public E add(E c) {
        em.persist(c);
        return c;
    }

    public boolean remove(String id) {
        E e = em.find(E(), id);
        if (e == null) return false;

        em.remove(e);
        return true;
    }

    public E update(Long id, E c) {
        E found = em.find(E(), id);
        if (found == null) throw new BadRequestException("Entity with id " + id + " not found.");

        c.setId(id);

        return em.merge(c);
    }

    private String typeSimple() { return E().getSimpleName(); }

    /**
     * @return a class instance of the first generic type parameter (E) of this Dao,
     * e.g. for EmployeeDao<Employee, Long>, it returns Employee.class.
     */
    @SuppressWarnings("unchecked")
    private Class<E> E() {
        ParameterizedType thisDaoClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) thisDaoClass.getActualTypeArguments()[0];
    }
}
