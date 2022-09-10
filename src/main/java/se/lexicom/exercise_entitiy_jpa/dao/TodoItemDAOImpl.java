package se.lexicom.exercise_entitiy_jpa.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicom.exercise_entitiy_jpa.exception.EntityNotFoundException;
import se.lexicom.exercise_entitiy_jpa.model.TodoItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoItemDAOImpl implements TodoItemDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public TodoItem save(TodoItem todoItem) {

        if (todoItem == null) throw new IllegalArgumentException("It is invalid");
        entityManager.persist(todoItem);

        return todoItem;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<TodoItem> findById(String id) {

        if (id == null) throw new IllegalArgumentException("Id is not valid");
        TodoItem todoItem = entityManager.find(TodoItem.class, id);
        return Optional.ofNullable(todoItem);
    }


    @Transactional
    @Override
    public TodoItem update(TodoItem todoItem) {

        return entityManager.merge(todoItem);

    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public TodoItem delete(TodoItem todoItem) {
        findById(todoItem.getTodoId()).orElseThrow(() -> new EntityNotFoundException("item not found"));
        entityManager.remove(todoItem);
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TodoItem> findByTitle(String title) {
        if (title ==null) throw new IllegalArgumentException("title is null");
        Query selectQuery = entityManager.createQuery("SELECT t FROM TodoItem t where t.title LIKE ?1");
        selectQuery.setParameter(1, title);
        return selectQuery.getResultList();

    }

    @Transactional(readOnly = true)
    @Override
    public List<TodoItem> findAll() {
        Query selectQuery = entityManager.createQuery("SELECT t FROM TodoItem t");
        return selectQuery.getResultList();

    }


    @Override
    public List<TodoItem> findByDeadLineBetween(LocalDateTime start, LocalDateTime end) {
        Query query = entityManager.createQuery("SELECT t FROM TodoItem t WHERE t.deadLine between ?1 and ?2 ");
        query.setParameter(1, start);
        query.setParameter(2, end);
        return query.getResultList();
    }

    @Override
    public List<TodoItem> findByDeadLineBefore(LocalDateTime end) {

        Query query = entityManager.createQuery("SELECT t FROM TodoItem t WHERE t.deadLine < ?1 ");
        query.setParameter(1, end);
        return query.getResultList();

    }

    @Override
    public List<TodoItem> findByDeadLineAfter(LocalDateTime start) {

        Query query = entityManager.createQuery("SELECT t FROM TodoItem t WHERE t.deadLine > ?1");
        query.setParameter(1, start);
        return query.getResultList();


    }

    @Transactional(readOnly = true)
    @Override
    public List<TodoItem> findByDone() {
        Query selectQuery = entityManager.createQuery("SELECT t FROM TodoItem t WHERE t.done = true");
        return selectQuery.getResultList();
    }


}
