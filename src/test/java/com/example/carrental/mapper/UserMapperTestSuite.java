package com.example.carrental.mapper;

import com.example.carrental.domain.User;
import com.example.carrental.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    void mapToUserDtoListTest() {

        //Given
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true));
        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);

        //Then
        assertEquals(userDtoList.get(0).getPhone(), "4568908654");
    }

    @Test
    void mapToUserTest() {
        //Given
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);
        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals(user.getId(), userDto.getId());
    }

    @Test
    void mapToUserDtoTest() {
        //Given
        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);
        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals(user.getId(), userDto.getId());
    }
}
