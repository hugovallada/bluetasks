package com.github.hugovallada.bluetasks.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.hugovallada.bluetasks.domain.user.AppUser;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    @Getter
    private Integer id;

    @Getter
    @Setter
    @NotEmpty(message = "A descrição é obrigatória")
    @Length(min = 3, max = 40, message = "O tamanho da tarefa é inválido")
    private String description;

    @Getter
    @Setter
    @NotNull(message = "A data da tarefa é obrigatória")
    @FutureOrPresent(message = "A data da tarefa não pode estar no passado")
    private LocalDate whenToDo;

    @Getter
    @Setter
    private Boolean done = false;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @Getter
    @Setter
    // @NotNull(message = "O usuário é obrigatório")
    private AppUser appUser;

    public Task(String description, LocalDate whenToDo, Boolean done, AppUser appUser) {
        this.description = description;
        this.whenToDo = whenToDo;
        this.done = done;
        this.appUser = appUser;
    }
}
