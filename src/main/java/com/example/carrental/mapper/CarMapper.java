package com.example.carrental.mapper;

import com.example.carrental.domain.Car;
import com.example.carrental.dto.CarDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
                .map(c -> mapToCarDto(c))
                .collect(Collectors.toList());
    }
}
