package my.home.todo;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Repository
public class ToDoRepository {
    private Flux<ToDo> todoFlux =
            Flux.fromIterable(Arrays.asList(
                            new ToDo("Do homework"),
                            new ToDo("Workout in morning", true),
                            new ToDo("Make dinner to night"),
                            new ToDo("Clean the studio", true)
                    )
            );

    public Mono<ToDo> findById(String id) {
        return Mono.from(todoFlux.filter(todo -> todo.getId().equals(id)));
    }

    public Flux<ToDo> findAll() {
        return todoFlux;
    }
}
