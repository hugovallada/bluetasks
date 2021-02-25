package com.github.hugovallada.bluetasks.domain.task;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PrePersist;

import com.github.hugovallada.bluetasks.domain.user.AppUser;
import com.github.hugovallada.bluetasks.domain.user.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TaskListener {

    private static AppUserRepository appUserRepository;

    @PrePersist // chama método antes da persistência
    public void onPrePersistHandler(Task task) {
        if (task.getAppUser() == null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            AppUser appUser = appUserRepository.findByUsername(username);

            if (appUser == null)
                throw new EntityNotFoundException("O usuário " + username + " não foi encontrado");

            task.setAppUser(appUser);

        }
    }

    @Autowired
    public void init(AppUserRepository appUserRepository) {
        TaskListener.appUserRepository = appUserRepository;
    }

}
