package com.github.hugovallada.bluetasks.infrastructure.web.security;

import com.github.hugovallada.bluetasks.domain.user.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Autowired
    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser = appUserRepository.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(appUser);
    }

}
