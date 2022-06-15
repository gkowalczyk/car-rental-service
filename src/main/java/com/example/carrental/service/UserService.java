package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.User;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.exception.UserNotFoundException;
import com.example.carrental.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {
        user.setActive(true);
        return userRepository.save(user);
    }

    public User blockUser(final User user) {
        user.setActive(false);
        return userRepository.save(user);
    }

    public String getJWTToken(String username) {

        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                .builder()
                .setId("id")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "login_token " + token;

    }

    public void saveUserData(String username, String token) {
        User user = new User();
        user.setLogin(username);
        user.setPassword(token);
        user.setActive(true);
        userRepository.save(user);
    }

    public void deleteUser(final Long id) throws UserNotFoundException {
        try {
            userRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new UserNotFoundException("User not found for ID=" + id);
        }
    }

    public List<User> getAllUsersFromDb() {
        return userRepository.findAll();
    }
}