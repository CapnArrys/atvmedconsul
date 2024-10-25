package br.com.arrys.medicalconsult.consulta.services;

import br.com.arrys.medicalconsult.consulta.domain.Consulta;
import br.com.arrys.medicalconsult.consulta.repositories.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @InjectMocks
    private ConsultaService consultaService;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void cadastrarConsulta() {

        Consulta consulta = new Consulta();
        consulta.setProfissional("dr roberto");


        when(consultaRepository.save(any(Consulta.class))).thenReturn(consulta);


        var resultado = consultaService.cadastrarConsulta(consulta);


        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("dr roberto", resultado.getProfissional())
        );

    }

    @Test
    void listarConsultas(){
        Consulta consulta1 = new Consulta();
        consulta1.setProfissional("Profissional1");
        Consulta consulta2 = new Consulta();
        consulta2.setProfissional("Profissional2");
        List<Consulta> consultas = new ArrayList<>();
        consultas.add(consulta1);
        consultas.add(consulta2);

        when(consultaRepository.findAll()).thenReturn(consultas);

        var resultado = consultaService.listarConsultas();

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals(2, resultado.size()),
                () -> assertEquals("Profissional1", resultado.get(0).getProfissional()),
                () -> assertEquals("Profissional2", resultado.get(1).getProfissional())
        );
    }

    @Test
    void buscarConsulta(){
        Consulta consulta = new Consulta();
        consulta.setProfissional("Profissional");

        when(consultaRepository.findById(consulta.getIdConsulta())).thenReturn(Optional.of(consulta));

        var resultado = consultaService.buscarConsulta(consulta.getIdConsulta());

        assertAll(
                () -> assertNotNull(resultado),
                () -> assertEquals("Profissional", consulta.getProfissional())
        );
    }

    @Test
    void deletarConsulta(){
        Consulta consulta = new Consulta();

        when(consultaRepository.findById(consulta.getIdConsulta())).thenReturn(Optional.of(consulta));

        consultaService.excluirConsulta(consulta.getIdConsulta());

        verify(consultaRepository, times(1)).delete(consulta);
    }

}