package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnTurno() {
        // Arrange
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L, 1L, LocalDateTime.now());

        // Act
        TurnoSalidaDto turnoSalidaDto = null;
        try {
            turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);
        } catch (BadRequestException e) {
            fail("Se produjo una excepción al registrar el turno: " + e.getMessage());
        }

        // Assert
        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());
        assertEquals(turnoEntradaDto.getPacienteId(), turnoSalidaDto.getPacienteSalidaDto().getId());
        assertEquals(turnoEntradaDto.getOdontologoId(), turnoSalidaDto.getOdontologoSalidaDto().getId());
        // Puedes agregar más aserciones según la estructura de tu DTO
    }
}