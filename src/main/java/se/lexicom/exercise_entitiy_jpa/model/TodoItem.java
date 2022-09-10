package se.lexicom.exercise_entitiy_jpa.model;

import jdk.jfr.Description;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class TodoItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String todoId;

    @Column(length = 200,nullable = false)
    private String title;
    @Column(length = 200,nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime deadLine;
    @Column(columnDefinition = "bit not null default false")
    private boolean done ;


    //------------>Constructors<-------------


    public TodoItem(String title, String description, LocalDateTime deadLine) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
    }

    public TodoItem() {
    }
    //------------->Customs methods<---------------


    //------------->Setter and Getter<-------------


    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return done == todoItem.done && Objects.equals(todoId, todoItem.todoId) && Objects.equals(title, todoItem.title) && Objects.equals(description, todoItem.description) && Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoId, title, description, deadLine, done);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "toDoId='" + todoId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }
}
