package my.home.todo.domain;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ToDo {

    @NotNull
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", type = org.hibernate.id.uuid.UuidGenerator.class)
    private String id;

    @Valid
    @NotNull(message = "поле description должно быть заполнено")
    @NotBlank
    private String description;

    @Column(insertable = true, updatable = false)
    private LocalDateTime created;

    private LocalDateTime modified;

    private boolean completed;

    public ToDo(String description) {
        this.description = description;
    }

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
}
