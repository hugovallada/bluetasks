package com.github.hugovallada.bluetasks.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.github.hugovallada.bluetasks.domain.user.AppUser;

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
    private String description;

    @Getter
    @Setter
    private LocalDate whenToDo;

    @Getter
    @Setter
    private Boolean done = false;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    @Getter
    @Setter
    private AppUser appUser;

    public Task(String description, LocalDate whenToDo, Boolean done, AppUser appUser) {
        this.description = description;
        this.whenToDo = whenToDo;
        this.done = done;
        this.appUser = appUser;
    }
}
