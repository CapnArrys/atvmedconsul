package br.com.arrys.medicalconsult.consulta.resources;

import br.com.arrys.medicalconsult.consulta.domain.Consulta;
import br.com.arrys.medicalconsult.consulta.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaResource {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta){
        Consulta novaConsulta = consultaService.cadastrarConsulta(consulta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(novaConsulta.getIdConsulta()).toUri();
        return ResponseEntity.created(uri).body(novaConsulta);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarConsultas(){
        List<Consulta> consultas = consultaService.listarConsultas();
        return ResponseEntity.ok().body(consultas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Consulta> buscarConsulta(@PathVariable Long id){
        Consulta consulta = consultaService.buscarConsulta(id);
        return ResponseEntity.ok().body(consulta);
    }

    @PutMapping("/{id}")
    public Consulta atualizarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        return consultaService.atualizarConsulta(id, consulta);

    }

    @DeleteMapping("/{id}")
    public void excluirConsulta(@PathVariable Long id){
        consultaService.excluirConsulta(id);
    }





}