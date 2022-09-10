package se.lexicom.exercise_entitiy_jpa.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicom.exercise_entitiy_jpa.dao.AppUserDAO;
import se.lexicom.exercise_entitiy_jpa.dao.TodoItemDAO;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Transactional
public class MyCommandLineRunner implements CommandLineRunner {


    private final AppUserDAO appUserDAO;
    private final EntityManager entityManager;
    private final TodoItemDAO todoItemDAO;


    @Autowired
    public MyCommandLineRunner(AppUserDAO appUserDAO, EntityManager entityManager, TodoItemDAO todoItemDAO) {
        this.appUserDAO = appUserDAO;
        this.entityManager = entityManager;
        this.todoItemDAO = todoItemDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("this is the output of My Spring boot");



        AppUser user1 = new AppUser("April110", "Mason", "Forouzmand",
                LocalDate.parse("1986-04-11"), "12344321");

        AppUser user2 = new AppUser("Safa", "Sam", "Rezai", LocalDate.parse("1984-10-10"), "0000");
        AppUser user3 = new AppUser("Si007", "Simon", "Elbrink", LocalDate.parse("1992-03-18"), "12352");

        user1=appUserDAO.save(user1);
        user2 = appUserDAO.save(user2);
        user3 = appUserDAO.save(user3);

        TodoItem item1 = new TodoItem("Reading code scrips", "Stack OverFLow", LocalDateTime.parse("2022-10-29T11:30"));
        item1 = todoItemDAO.save(item1);
        TodoItem item2 = new TodoItem("OOP", "WOrkshop in the class", LocalDateTime.parse("2022-12-12T08:00"));
        item2 = todoItemDAO.save(item2);

        System.out.println(todoItemDAO.findAll()) ;
        System.out.println(todoItemDAO.findByDeadLineBefore(LocalDateTime.parse("2022-12-25T12:00")));











    }


}
