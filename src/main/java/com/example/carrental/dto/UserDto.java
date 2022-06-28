package com.example.carrental.dto;


import lombok.Getter;
import java.util.List;

@Getter

public class UserDto {

    private Long id;

    private String surname;

    private String lastname;

    private String login;

    private String password;

    private String mail;

    private String phone;

    private List<RentDto> rentList;

    private boolean isActive;


    public UserDto(Long id, String surname, String lastname, String login, String password, String mail, String phone, boolean isActive) {
        this.id = id;
        this.surname = surname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
        this.isActive = isActive;
    }
}