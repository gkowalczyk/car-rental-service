package com.example.carrental.mapper;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.Rent;
import com.example.carrental.domain.User;
import com.example.carrental.dto.CarDto;
import com.example.carrental.dto.RentDto;
import com.example.carrental.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {
    private final UserMapper userMapper;
    private final CarMapper carMapper;

    public RentMapper(UserMapper userMapper, CarMapper carMapper) {
        this.userMapper = userMapper;
        this.carMapper = carMapper;
    }

    public Rent mapToRent(RentDto rentDto) {
        UserDto userDto = rentDto.getUserDto();
        CarDto carDto = rentDto.getCarDto();

        return new Rent(
                rentDto.getId(),
                userMapper.mapToUser(userDto),
                carMapper.mapToCar(carDto),
                rentDto.getStartRent(),
                rentDto.getEndRent(),
                rentDto.getTotalCost(),
                rentDto.isPaid(),
                rentDto.getRentStatus()
        );
    }

    public RentDto mapToRentDto(Rent rent) {
        User user = rent.getUser();
        Car car = rent.getCar();

        return new RentDto(
                rent.getId(),
                userMapper.mapToUserDto(user),
                carMapper.mapToCarDto(car),
                rent.getStartRent(),
                rent.getEndRent(),
                rent.getTotalCost(),
                rent.isPaid(),
                rent.getRentStatus()
        );
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rentList) {
        return rentList.stream()
                .map(r -> mapToRentDto(r))
                .collect(Collectors.toList());
    }
}