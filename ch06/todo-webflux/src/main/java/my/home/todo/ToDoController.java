package my.home.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ToDoController {
    private ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("/todo/{id}")
    public Mono<ToDo> getTodo(@PathVariable String id) {
        return this.toDoRepository.findById(id);
    }

    @GetMapping("/todo")
    public Flux<ToDo> getTodos() {
        return this.toDoRepository.findAll();
    }
}
