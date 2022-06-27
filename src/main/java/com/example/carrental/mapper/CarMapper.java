package com.example.carrental.mapper;

import com.example.carrental.domain.Car;
import com.example.carrental.dto.CarDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public Car mapToCar(CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getRegistration(),
                carDto.getModel(),
                carDto.getCompany(),
                carDto.getCategory(),
                carDto.getGearBox(),
                carDto.getDailyCost(),
                carDto.isAvailable()
        );
    }

    public CarDto mapToCarDto(Car car) {
        return new CarDto(
                car.getId(),
                car.getRegistration(),
                car.getModel(),
                car.getCompany(),
                car.getCategory(),
                car.getGearBox(),
                car.getDailyCost(),
                car.isAvailable()
        );
    }

    public List<CarDto> mapToCarDtoList(List<Car> carList) {
        return carList.stream()
                .map(c -> new CarDto(c.getId(), c.getRegistration(), c.getModel(), c.getCompany(), c.getCategory(), c.getGearBox(), c.getDailyCost(), c.isAvailable()))
                .collect(Collectors.toList());
    }

    public List<Car> mapToCarList(List<CarDto> carListDto) {
        return carListDto.stream()
                .map(c -> new Car(c.getId(), c.getRegistration(), c.getModel(), c.getCompany(), c.getCategory(), c.getGearBox(), c.getDailyCost(), c.isAvailable()))
                .collect(Collectors.toList());
    }
}
