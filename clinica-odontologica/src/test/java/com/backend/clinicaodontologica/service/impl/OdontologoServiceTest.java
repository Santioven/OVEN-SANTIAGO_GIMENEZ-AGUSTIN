package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {



    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    void deberiaRegistrarseUnPacienteDeNombreCarlos_yRetornarSuId() {
        //arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("21312313", "Carlos", "Bianchi");

        //act
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        //assert
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Carlos", odontologoSalidaDto.getNombre());

    }
    @Test
    @Order(2)
    void deberiaRegistrarseUnPacienteDeNombreJuan_yRetornarSuId() {
        //arrange
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("12345678", "Mateo", "Pirez");

        //act
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        //assert
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("Mateo", odontologoSalidaDto.getNombre());
    }

    @Test
    @Order(3)
    void deberiaEliminarseElOdontologoConId1() {


        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }


    @Test
    @Order(4)
    void deberiaDevolverUnaListaConUnOdontologo() {
        List<OdontologoSalidaDto> odontologos = odontologoService.listarOdontologos();

        assertTrue(odontologos.size() == 1);
    }


}

