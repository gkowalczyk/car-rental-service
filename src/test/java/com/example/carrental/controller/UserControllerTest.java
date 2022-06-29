package com.example.carrental.controller;


import com.example.carrental.domain.User;
import com.example.carrental.dto.UserDto;
import com.example.carrental.mapper.UserMapper;
import com.example.carrental.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringJUnitWebConfig
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserService userService;

    @Test
    void shouldFetchUsersBoards() throws Exception {

        List<UserDto> userDtoList = List.of(new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true));
        List<User> userList = List.of(new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true));
        when(userService.getAllUsersFromDb()).thenReturn(userList);
        when(userMapper.mapToUserDtoList((userList))).thenReturn(userDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/users/allusers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].surname", Matchers.is("Jan")));
    }
}

