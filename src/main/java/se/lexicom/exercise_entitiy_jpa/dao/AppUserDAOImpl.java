package se.lexicom.exercise_entitiy_jpa.dao;

import org.springframework.stereotype.Repository;
import se.lexicom.exercise_entitiy_jpa.model.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class AppUserDAOImpl implements AppUserDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public AppUser findById(int id) {
        if (id <= 0) throw new RuntimeException("Invalid Id");

        AppUser appUser = entityManager.find(AppUser.class, id);
        return appUser;

    }


    @Transactional
    @Override
    public AppUser save(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("user is  null");
        entityManager.persist(appUser);
        return appUser;
    }


    @Transactional
    @Override
    public void delete(AppUser appUser) {
        findById(appUser.getUserId());
        entityManager.remove(appUser);
    }
}
