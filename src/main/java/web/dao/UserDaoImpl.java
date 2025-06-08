package web.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import web.model.User;


import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> findAll() {
        return em
                .createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException(
                    "User with id: " + id + " not found"
            );
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        findById(user.getId());
        em.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        em.remove(user);
    }
}
