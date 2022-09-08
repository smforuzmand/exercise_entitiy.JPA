package se.lexicom.exercise_entitiy_jpa.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicom.exercise_entitiy_jpa.daoImpl.AppUserDAO;
import se.lexicom.exercise_entitiy_jpa.daoImpl.AppUserDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
@Transactional
public class MyCommandLineRunner implements CommandLineRunner {


    private final AppUserDAO appUserDAO;
    private final EntityManager entityManager;

    public MyCommandLineRunner(AppUserDAO appUserDAO, EntityManager entityManager) {
        this.appUserDAO = appUserDAO;
        this.entityManager = entityManager;
    }

    @Override
    public void run(String... args) throws Exception {

        AppUser user1 = new AppUser("April110", "Mason", "Forouzmand",
                LocalDate.parse("1986-04-11"), "12344321");

        appUserDAO.save(user1);
        user1=appUserDAO.findById(1);

        //entityManager.remove(user1);

//findById TODO


    }


}
