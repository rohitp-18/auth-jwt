package com.app.securityspring.services;

import com.app.securityspring.dto.UserReqDto;
import com.app.securityspring.dto.UserResDto;
import com.app.securityspring.entity.Users;
import com.app.securityspring.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepo userRepo;

    @Autowired
    public JwtService jwtService;

    @Autowired
    public AuthenticationManager authenticationManager;

    public UserResDto register(UserReqDto userReqDto){
        Users users = userRepo.save(mapToEntity(userReqDto));

        return mapToDto(users);
    }


    public String login(UserReqDto userReqDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userReqDto.getUsername(), userReqDto.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateKey(userReqDto.getUsername());
        }

        return "Fail";
    }


    public Users mapToEntity(UserReqDto userReqDto){
        Users users = new Users();
        users.setPassword(new BCryptPasswordEncoder(10).encode(userReqDto.getPassword()));
        users.setUsername(userReqDto.getUsername());
        return users;
    }

    public UserResDto mapToDto(Users users){
        UserResDto res = new UserResDto();
        res.setId(users.getId());
        res.setCreatedAt(users.getCreatedAt());
        res.setUsername(users.getUsername());
        return res;
    }
}
