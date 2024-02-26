package my.home.todo.validation;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ToDoValidationErrorBuilder {
    public static ToDoValidationError fromBindingErrors(Errors errors) {
        ToDoValidationError error = new ToDoValidationError(
                "Validation failed " +
                        errors.getErrorCount() + "error(s)"
        );
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationErrors(objectError.getDefaultMessage());
        }
        return error;
    }
}
