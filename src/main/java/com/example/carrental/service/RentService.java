package com.example.carrental.service;


import com.example.carrental.domain.*;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.exception.EquipmentNotFoundException;
import com.example.carrental.exception.RentNotFoundException;
import com.example.carrental.exception.UserNotFoundException;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.EquipmentRepository;
import com.example.carrental.repository.RentRepository;
import com.example.carrental.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RentService {
    public static final String ECONOMIC = "ECONOMIC";
    public static final String MIDDLE = "MIDDLE";
    public static final String VIP = "VIP";


    private final RentRepository repository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final EquipmentRepository equipmentRepository;


    public RentService(RentRepository repository, UserRepository userRepository, CarRepository carRepository, EquipmentRepository equipmentRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.equipmentRepository = equipmentRepository;
    }

    public List<Rent> getAllRentsFromDb() {
        Iterable<Rent> rentIterable = repository.findAll();
        List<Rent> rentList = new ArrayList<>();
        rentIterable.forEach(rentList::add);
        return rentList;
    }

    public Rent getRentById(final Long id) throws RentNotFoundException {
        return repository.findById(id).orElseThrow(() -> new RentNotFoundException(
                "Rent not found for id: " + id));
    }

    public Rent createNewCardRent(final Long userId, final Long carId) throws UserNotFoundException, CarNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("Id car:" + userId + ":not found"));
        Car car = carRepository.findById(carId).orElseThrow(() ->
                new CarNotFoundException("Id car:" + carId + ":not found"));
        Rent rent = new Rent();
        rent.setUser(user);
        rent.setCar(car);
        rent.setRentStatus(RentStatus.BOOKING);
        rent.setStartRent(rent.getStartRent());
        rent.setEndRent((rent.getEndRent()));
        rent = repository.save(rent);
        userRepository.save(user);
        log.info("Reservation card for:" + userId + "confirmed");
        return rent;
    }

    public void cancelCardRent(final Long id) throws RentNotFoundException {

        try {
            repository.deleteById(id);
            Rent rent = new Rent();
            rent.setRentStatus(RentStatus.CANCELING);
            repository.save(rent);
        } catch (DataAccessException e) {
            throw new RentNotFoundException("Rent not found for ID=" + id);
        }
    }

    public Rent updateRent(final Rent rent, final Long rentId) throws RentNotFoundException {
        Optional<Rent> optionalRent = repository.findById(rentId);
        Rent rentUpdate = optionalRent.orElseThrow(() ->
                new RentNotFoundException("Id rent:" + rentId + ":not found"));
        log.info("Update rent to DB");
        rentUpdate.setStartRent(rent.getStartRent());
        rentUpdate.setEndRent(rent.getEndRent());
        rentUpdate.setCar(rent.getCar());
        rentUpdate.setRentStatus(RentStatus.REFRESHING);
        return repository.save(rentUpdate);
    }

    public BigDecimal calculateRentCostFactory(Long rentId, Long equipmentId, String param) throws RentNotFoundException, EquipmentNotFoundException {

        Optional<Rent> optionalRent = repository.findById(rentId);
        Rent rentUpdate = optionalRent.orElseThrow(() ->
                new RentNotFoundException("Id rent:" + rentId + ":not found"));
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(equipmentId);
        Equipment equipment = optionalEquipment.orElseThrow(() ->
                new EquipmentNotFoundException("Id equipment:" + equipmentId + ":not found"));

        switch (param) {
            case ECONOMIC:
                CarOrder carOrder = new LowComfortCar();
                BigDecimal calculate = carOrder.getPrice();
                BigDecimal rentTime = new BigDecimal(ChronoUnit.DAYS.between(rentUpdate.getStartRent(), rentUpdate.getEndRent()));
                rentUpdate.setTotalCost(calculate.multiply(rentTime).add(equipment.getPrice()));
                rentUpdate.setRentStatus(RentStatus.IN_PROGRESS);
                repository.save(rentUpdate);
                log.info("Calculation for case:" + ECONOMIC + " confirmed");
                return calculate;
            case MIDDLE:
                CarOrder carOrderPlus = new LowComfortCar();
                carOrderPlus = new MiddleComfortCar(carOrderPlus);
                BigDecimal calculate1 = carOrderPlus.getPrice();
                BigDecimal rentTime1 = new BigDecimal(ChronoUnit.DAYS.between(rentUpdate.getStartRent(), rentUpdate.getEndRent()));
                rentUpdate.setTotalCost(calculate1.multiply(rentTime1).add(equipment.getPrice()));
                rentUpdate.setRentStatus(RentStatus.IN_PROGRESS);
                repository.save(rentUpdate);
                log.info("Calculation for case:" + MIDDLE + " confirmed");
                return calculate1;
            case VIP:
                CarOrder carOrderPlusPlus = new LowComfortCar();
                carOrderPlusPlus = new MiddleComfortCar(carOrderPlusPlus);
                carOrderPlusPlus = new PremiumComfortCar(carOrderPlusPlus);
                BigDecimal calculate2 = carOrderPlusPlus.getPrice();
                BigDecimal rentTime2 = new BigDecimal(ChronoUnit.DAYS.between(rentUpdate.getStartRent(), rentUpdate.getEndRent()));
                rentUpdate.setTotalCost(calculate2.multiply(rentTime2).add(equipment.getPrice()));
                rentUpdate.setRentStatus(RentStatus.IN_PROGRESS);
                repository.save(rentUpdate);
                log.info("Calculation for case:" + VIP + " confirmed");
                return calculate2;
            default:
                return BigDecimal.ZERO;
        }
    }
}
