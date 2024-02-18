package my.home.todo.repository;

import my.home.todo.domain.Todo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<Todo, String> {

}
