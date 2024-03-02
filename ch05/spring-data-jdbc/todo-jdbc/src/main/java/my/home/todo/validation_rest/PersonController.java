package my.home.todo.validation_rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<Person> getByLogin(@RequestParam("login") String login) {
        return ResponseEntity.ok(personService.getByLoginOrThrown(login));
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(personService.getById(id).orElseThrow());
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundException.class)
//    public ErrorMessage handleException(NotFoundException exception) {
//        return new ErrorMessage(exception.getMessage());
//    }

}

