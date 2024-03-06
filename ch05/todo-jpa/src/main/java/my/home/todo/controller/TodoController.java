package my.home.todo.controller;


import jakarta.validation.Valid;
import my.home.todo.domain.ToDo;
import my.home.todo.domain.ToDoBuilder;
import my.home.todo.repository.CommonRepository;
import my.home.todo.validation.ToDoValidationErrorBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final CommonRepository<ToDo> repository;

    public TodoController(CommonRepository<ToDo> repository) {
        this.repository = repository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDo>> getToDos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id) {
        return ResponseEntity.ok(repository.findById(id));
    }

//    @PostMapping("/todo/{id}")
//    public ResponseEntity<ToDo> setCompleted(@PathVariable String id) {
//        ToDo result = repository.findById(id);
//        result.setCompleted(true);
//        repository.save(result);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .buildAndExpand(result.getId()).toUri();
//        return ResponseEntity.ok()
//                .header("Location", location.toString())
//                .build();
//    }


    @DeleteMapping("/todo/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable String id) {
        repository.delete(ToDoBuilder.create().withId(id).build());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todo")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo) {
        repository.delete(toDo);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/todo", method = {RequestMethod.POST,
            RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo,
                                        Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }

        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ToDoValidationError handleException(Exception exception) {
//        return new ToDoValidationError(exception.getMessage());
//    }
}
