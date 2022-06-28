package com.example.carrental.controller;


import com.example.carrental.domain.Equipment;
import com.example.carrental.dto.EquipmentDto;
import com.example.carrental.mapper.EquipmentMapper;
import com.example.carrental.service.EquipmentService;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringJUnitWebConfig
@WebMvcTest(EquipmentControllerTest.class)
public class EquipmentControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EquipmentService equipmentService;
    @MockBean
    private EquipmentMapper equipmentMapper;


        @Test
        void shouldFetchCarDtoList() throws Exception {
            List<EquipmentDto> equipmentDtoList = List.of(new EquipmentDto(1L,"extra gas", new BigDecimal(50)));
            List<Equipment> equipmentList = List.of(new Equipment(1L,"extra gas", new BigDecimal(50)));
            when(equipmentService.getAllAccessories()).thenReturn(equipmentList);
            when(equipmentMapper.mapToEquipmentDtoList(equipmentList)).thenReturn(equipmentDtoList);
            mockMvc
                    .perform(MockMvcRequestBuilders
                            .get("/v1/accessories/allaccessories")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect((MockMvcResultMatchers.status()).is(200))
                    .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                   .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                    .andExpect(jsonPath("$[0].description", Matchers.is("extra gas")))
                    .andExpect(jsonPath("$[0].price", Matchers.is(50)));
    }
}
