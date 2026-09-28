package com.app.securityspring.services;

import com.app.securityspring.dto.UserPrincipal;
import com.app.securityspring.entity.Users;
import com.app.securityspring.repo.UserRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServices implements UserDetailsService {

    @Autowired
    public UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepo.findUsersByUsername(username);

        if(user.isEmpty()){
            System.out.println("user not found"+username);
            throw  new UsernameNotFoundException("user not found");
        }
        System.out.println(user.get());
        return new UserPrincipal(user.get());
    }
}
