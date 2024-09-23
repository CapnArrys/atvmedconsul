package br.com.arrys.medicalconsult.consulta.services;

import br.com.arrys.medicalconsult.consulta.domain.Consulta;
import br.com.arrys.medicalconsult.consulta.repositories.ConsultaRepository;
import br.com.arrys.medicalconsult.usuario.domain.Usuario;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta cadastrarConsulta(Consulta consulta){
        consulta.setIdConsulta(null);
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarConsultas(){
        return consultaRepository.findAll();
    }

    public Consulta buscarConsulta(Long id){
        return consultaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado: ", id));
    }

    public Consulta atualizarConsulta(Long id, Consulta consulta){
        Consulta consultaExistente = consultaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        consultaExistente.setDataConsulta(consulta.getDataConsulta());
        consultaExistente.setEspecialidade(consulta.getEspecialidade());
        consultaExistente.setProfissional(consulta.getProfissional());

        return consultaRepository.save(consultaExistente);

    }

    public void excluirConsulta(Long id) {
        Consulta consultaParaSerDeletado = consultaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        consultaRepository.delete(consultaParaSerDeletado);

    }

}