package my.home.todo.validation_rest.valid;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    @NotBlank(message = "[Address] The city is required.")
    private String city;

    @NotBlank(message = "[Address] The street name is required.")
    private String street;
}
