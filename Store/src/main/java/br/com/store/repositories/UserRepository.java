package br.com.store.repositories;

import br.com.store.models.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserRepository implements Serializable{

    private final EntityManager entityManager;

    public UserRepository(EntityManager em) {
        this.entityManager = em;
    }

    public boolean create(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            //log e
            return false;
        }
    }

    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

}
