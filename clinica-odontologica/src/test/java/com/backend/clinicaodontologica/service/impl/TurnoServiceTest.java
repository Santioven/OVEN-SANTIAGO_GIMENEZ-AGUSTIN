package com.backend.clinicaodontologica.service.impl;
import com.backend.clinicaodontologica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.entrada.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.salida.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.TurnoSalidaDto;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnTurno() {
        // Arrange: Crear un paciente temporal
        PacienteEntradaDto pacienteTemp = new PacienteEntradaDto();
        pacienteTemp.setNombre("Carlos");
        // Setear otros atributos necesarios

        // Registrar el paciente temporal en la base de datos
        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(pacienteTemp);

        // Arrange: Crear un odontólogo temporal
        OdontologoEntradaDto odontologoTemp = new OdontologoEntradaDto();
        odontologoTemp.setNombre("Dr.Gavilan");
        // Setear otros atributos necesarios

        // Registrar el odontólogo temporal en la base de datos
        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologoTemp);

        // Arrange: Crear un turno con los IDs de paciente y odontólogo temporales
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto();
        turnoEntradaDto.setPacienteId(pacienteRegistrado.getId());
        turnoEntradaDto.setOdontologoId(odontologoRegistrado.getId());
        // Setear otros atributos necesarios

        // Act: Registrar el turno
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

    @Test
    @Order(2)
    void deberiaEliminarseElTurnoConId1() {


        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }
    @Test
    @Order(3)
    void deberiaDevolverUnaListaVaciaDeTurnos() {
        List<TurnoSalidaDto> turnos = turnoService.listarTurnos();

        assertTrue(turnos.size() == 0);
    }
}