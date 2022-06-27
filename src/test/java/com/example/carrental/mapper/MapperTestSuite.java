package com.example.carrental.mapper;

import com.example.carrental.domain.Car;
import com.example.carrental.dto.CarDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTestSuite {

    @Autowired
    private CarMapper carMapper;

    private static int testCounter = 0;

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("This is the beginning of tests.");
    }
    @AfterAll
    public static void afterAllTests() {
        System.out.println("All tests are finished.");
    }
    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
    }
@Test
    void mapToCarDtoListTest() {
        //Given
    List<Car> carList = new LinkedList<>();
    carList.add(new Car(1L,"DW 1234", "FORD", "FOCUS", "ECONOMIC", "AUTOMATIC", new BigDecimal(100), true));

    //When
    List<CarDto> carListDto = carMapper.mapToCarDtoList(carList);
    System.out.println(carListDto.size());
    System.out.println(carList.size());
    //Then
    assertEquals(carListDto.get(0).getModel().equals("FORD"),"FORD" );


}

}
