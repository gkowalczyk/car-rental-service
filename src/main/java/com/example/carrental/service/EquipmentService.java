package com.example.carrental.service;

import com.example.carrental.domain.Car;
import com.example.carrental.domain.Equipment;
import com.example.carrental.domain.Rent;
import com.example.carrental.domain.RentStatus;
import com.example.carrental.exception.CarNotFoundException;
import com.example.carrental.exception.EquipmentNotFoundException;
import com.example.carrental.exception.RentNotFoundException;
import com.example.carrental.repository.EquipmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllAccessories() {
        return equipmentRepository.findAll();
    }

    public void deleteEquipmentFromDB(final Long id) throws EquipmentNotFoundException {
        try {
            equipmentRepository.deleteById(id);
            log.info("Delete equipment from DB");
        } catch (DataAccessException e) {
            throw new EquipmentNotFoundException("Equipment not found for ID=" + id);
        }
    }

    public Equipment updateEquipment(final Equipment equipment, final Long equipmentId) throws EquipmentNotFoundException {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(equipmentId);
        Equipment update = optionalEquipment.orElseThrow(() ->
                new EquipmentNotFoundException("Id rent:" + equipmentId + ":not found"));
        log.info("Update equipment to DB");
        update.setPrice(equipment.getPrice());
        update.setDescription(equipment.getDescription());
        return equipmentRepository.save(update);
    }

    public Equipment addEquipment(final Equipment equipment) {
        log.info("Adding equipment to DB");
        return equipmentRepository.save(equipment);
    }
}