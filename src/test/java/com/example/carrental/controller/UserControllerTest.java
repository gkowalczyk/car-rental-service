package com.example.carrental.controller;


import com.example.carrental.domain.User;
import com.example.carrental.dto.UserDto;
import com.example.carrental.exception.UserNotFoundException;
import com.example.carrental.mapper.UserMapper;
import com.example.carrental.service.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void shouldFetchUsers() throws Exception {

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

    @Test
    void shouldDeleteUser() throws Exception {

        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        doNothing().when(userService).deleteUser(user.getId());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/users/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateUserTestSuite() throws Exception {
        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);


        //when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userService.blockUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        Gson gson = new Gson();
        String content = gson.toJson(userDto);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(content))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isActive", Matchers.is(true)));
    }

    @Test
    void shouldCreateNewUser() throws Exception {
        User user = new User(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);
        UserDto userDto = new UserDto(1L, "Jan", "Kowalski", "jkowalski", "yourjwttoken",
                "jankowalski@wp.pl", "4568908654", true);

        when(userService.createUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect((MockMvcResultMatchers.status()).is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", Matchers.is("jkowalski")));
    }
}

