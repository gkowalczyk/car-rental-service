package com.example.carrental.mapper;


import com.example.carrental.domain.User;
import com.example.carrental.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {


    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getSurname(),
                userDto.getLastname(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getMail(),
                userDto.getPhone(),
                userDto.isActive()
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getSurname(),
                user.getLastname(),
                user.getLogin(),
                user.getPassword(),
                user.getMail(),
                user.getPhone(),
                user.isActive()
        );
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(u -> mapToUserDto(u))
                .collect(Collectors.toList());
    }
}