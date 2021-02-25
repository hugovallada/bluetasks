package com.github.hugovallada.bluetasks.test;

import java.time.LocalDate;

import com.github.hugovallada.bluetasks.domain.task.Task;
import com.github.hugovallada.bluetasks.domain.task.TaskRepository;
import com.github.hugovallada.bluetasks.domain.user.AppUser;
import com.github.hugovallada.bluetasks.domain.user.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

@Component
public class InsertTestData {

    private TaskRepository taskRepository;

    private AppUserRepository appUserRepository;

    @Autowired
    public InsertTestData(TaskRepository taskRepository, AppUserRepository appUserRepository) {
        this.taskRepository = taskRepository;
        this.appUserRepository = appUserRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var appUser = new AppUser("John", encoder.encode("123456"), "John Coder");
        appUserRepository.save(appUser);

        var appUser2 = new AppUser("Paul", encoder.encode("123456"), "Paul Dev");
        appUserRepository.save(appUser2);

        var baseDate = LocalDate.parse("2022-02-01");

        for (int i = 1; i <= 10; i++) {
            var task = new Task("Tarefa #" + i, baseDate.plusDays(i), false, appUser);
            taskRepository.save(task);
        }

        for (int i = 11; i <= 16; i++) {
            var task = new Task("Tarefa # " + i, baseDate.plusDays(i), false, appUser2);
            taskRepository.save(task);
        }
    }

}
