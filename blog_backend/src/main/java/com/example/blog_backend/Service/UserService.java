package com.example.blog_backend.Service;

import com.example.blog_backend.CustomException.CustomException;
import com.example.blog_backend.Dto.UserRequestDto;
import com.example.blog_backend.Entity.User;
import com.example.blog_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<User> createUser(UserRequestDto userRequestDto) {
        User user = convertDtoToEntity(userRequestDto);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    private User convertDtoToEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setMail(userRequestDto.getMail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(userRequestDto.getPassword());
        user.setPassword(hashedPassword);
        return user;
    }

    public String validateUser(UserRequestDto userRequestDto) {
        User user = userRepository.findByMail(userRequestDto.getMail());
        if(Objects.isNull(user)){
            throw new CustomException("user mail is not valid here");
        }
        String hashedPassword = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordMatches = encoder.matches(userRequestDto.getPassword(), hashedPassword);
        if(passwordMatches){
            return "UserId and Password match";
        }else{
            return "UserId and Password don't match";
        }
    }
}
